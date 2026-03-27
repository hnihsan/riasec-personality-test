package riasec.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riasec.backend.model.classes.*;
import riasec.backend.model.enums.Gender;
import riasec.backend.model.enums.PersonalityType;
import riasec.backend.repository.*;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class HollandCodeTestAttemptController {

    @Autowired
    HollandCodeTestQuestionRepository hollandCodeTestQuestionRepository;

    @Autowired
    ProfessionRepository professionRepository;

    @Autowired
    TestTakerRepository testTakerRepository;

    @Autowired
    HollandCodeTestRepository hollandCodeTestRepository;

    @Autowired
    HollandCodeTestAttemptRepository hollandCodeTestAttemptRepository;

    @PostMapping("/testAttempt")
    public ResponseEntity<Map<String, List<?>>> processAnswers(@RequestBody Map<String, Object> requestData) {
        try {
            //Prepare Data
            Map<Long, Boolean> answersJson = (Map<Long, Boolean>) requestData.get("questionAnswer");
            System.out.println(answersJson);
            Map<String, String> user = (Map<String, String>) requestData.get("user");
            Iterable<HollandCodeTestQuestion> questions = hollandCodeTestQuestionRepository.findAll();

            // Convert Map<Long, Boolean> to Map<HollandCodeTestQuestion, Boolean>
            Map<HollandCodeTestQuestion, Boolean> questionAnswers = new HashMap<>();
            for (HollandCodeTestQuestion question : questions) {
                Boolean answer = answersJson.get(String.valueOf(question.getId()));
                if (answer != null) {
                    questionAnswers.put(question, answer);
                }
            }

            //User Information
            String firstName = user.get("firstName");
            String lastName = user.get("lastName");
            String gender = user.get("gender");
            String email = user.get("email");

            Gender genderEnum;
            if (gender.equals("Male")) {
                genderEnum = Gender.MALE;
            } else if (gender.equals("Female")) {
                genderEnum = Gender.FEMALE;
            } else if (gender.equals("Non-Binary")) {
                genderEnum = Gender.NONBINARY;
            } else {
                genderEnum = Gender.OTHER;
            }

            //Check if user already exists
            TestTaker testTaker = testTakerRepository.findByEmailAddress(email);
            if (testTaker == null) {
                testTaker = new TestTaker(firstName, lastName, email, genderEnum);
                System.out.println("New user created");
            }
            testTakerRepository.save(testTaker);

            //Create a new testAttempt
            HollandCodeTestAttempt testAttempt = new HollandCodeTestAttempt();
            testAttempt.setTestTaker(testTaker);
            testAttempt.setQuestionAnswers(questionAnswers);

            Optional<HollandCodeTest> hollandCodeTest = hollandCodeTestRepository.findById(Integer.parseInt(String.valueOf(requestData.get("testId"))));
            if (hollandCodeTest.isPresent()) {
                HollandCodeTest hollandCodeTestObject = hollandCodeTest.get();
                testAttempt.setTest(hollandCodeTestObject);
            }

            // Step 1 — Score: replaces score.drl
            for (Map.Entry<HollandCodeTestQuestion, Boolean> entry : testAttempt.getQuestionAnswers().entrySet()) {
                if (Boolean.TRUE.equals(entry.getValue())) {
                    testAttempt.incrementScore(entry.getKey().getPersonalityType());
                }
            }

            System.out.println("Realistic Score: " + testAttempt.getScore(PersonalityType.REALISTIC));
            System.out.println("Artistic Score: " + testAttempt.getScore(PersonalityType.ARTISTIC));
            System.out.println("Investigative Score: " + testAttempt.getScore(PersonalityType.INVESTIGATIVE));
            System.out.println("Conventional Score: " + testAttempt.getScore(PersonalityType.CONVENTIONAL));
            System.out.println("Social Score: " + testAttempt.getScore(PersonalityType.SOCIAL));
            System.out.println("Enterprising Score: " + testAttempt.getScore(PersonalityType.ENTERPRISING));

            // Step 2 — Top-3 Holland code: replaces top-3-personalities-v2.drl
            List<PersonalityType> priorityOrder = Arrays.asList(
                PersonalityType.REALISTIC,
                PersonalityType.ENTERPRISING,
                PersonalityType.SOCIAL,
                PersonalityType.INVESTIGATIVE,
                PersonalityType.CONVENTIONAL,
                PersonalityType.ARTISTIC
            );
            List<PersonalityType> sorted = new ArrayList<>(Arrays.asList(PersonalityType.values()));
            sorted.sort((a, b) -> {
                int cmp = testAttempt.getScore(b) - testAttempt.getScore(a);
                if (cmp != 0) return cmp;
                return Integer.compare(priorityOrder.indexOf(a), priorityOrder.indexOf(b));
            });
            String hollandCode = sorted.stream().limit(3)
                .map(t -> String.valueOf(t.name().charAt(0)))
                .collect(Collectors.joining());
            testAttempt.setResult(Collections.singletonList(hollandCode));

            // Step 3 — Profession matching: replaces matchProfessions.drl
            Map<String, List<Profession>> professionResult = matchProfessions(hollandCode);

            hollandCodeTestAttemptRepository.save(testAttempt);

            //Sending Response to Frontend
            Map<String, List<?>> response = new HashMap<>();
            response.put("hollandCode", testAttempt.getResult());
            response.put("exactProfessions", professionResult.get("exactProfessions"));
            response.put("similarProfessions", professionResult.get("similarProfessions"));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/testAttempt/history")
    public ResponseEntity<List<Map<String, Object>>> getHistory(@RequestParam String email) {
        try {
            List<HollandCodeTestAttempt> attempts = hollandCodeTestAttemptRepository.findByTestTakerEmailAddress(email);
            List<Map<String, Object>> result = new ArrayList<>();

            for (HollandCodeTestAttempt attempt : attempts) {
                String hollandCode = attempt.getResult() != null && !attempt.getResult().isEmpty()
                    ? attempt.getResult().get(0) : "";
                Map<String, List<Profession>> professionResult = matchProfessions(hollandCode);

                Map<String, Object> entry = new HashMap<>();
                entry.put("id", attempt.getId());
                entry.put("date", attempt.getDate());
                entry.put("hollandCode", hollandCode);
                entry.put("testTitle", attempt.getTest() != null
                    ? attempt.getTest().getTitle() : "");
                entry.put("exactProfessions", professionResult.get("exactProfessions"));
                entry.put("similarProfessions", professionResult.get("similarProfessions"));
                result.add(entry);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Map<String, List<Profession>> matchProfessions(String hollandCode) {
        List<Profession> exactMatches = new ArrayList<>();
        List<Profession> similarMatches = new ArrayList<>();
        for (Profession profession : professionRepository.findAll()) {
            String profCode = profession.getHollandCode();
            if (hollandCode.equals(profCode)) {
                exactMatches.add(profession);
            } else {
                long shared = hollandCode.chars()
                    .filter(c -> profCode.indexOf(c) >= 0)
                    .count();
                if (shared >= 3) {
                    similarMatches.add(profession);
                }
            }
        }
        Map<String, List<Profession>> result = new HashMap<>();
        result.put("exactProfessions", exactMatches);
        result.put("similarProfessions", similarMatches);
        return result;
    }
}
