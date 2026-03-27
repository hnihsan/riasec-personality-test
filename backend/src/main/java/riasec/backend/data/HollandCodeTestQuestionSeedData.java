package riasec.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riasec.backend.model.classes.HollandCodeTest;
import riasec.backend.model.classes.HollandCodeTestQuestion;
import riasec.backend.model.enums.PersonalityType;
import riasec.backend.repository.HollandCodeTestQuestionRepository;
import riasec.backend.repository.HollandCodeTestRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class HollandCodeTestQuestionSeedData implements CommandLineRunner {
    @Autowired
    HollandCodeTestQuestionRepository hollandCodeTestQuestionRepository;

    @Autowired
    HollandCodeTestRepository hollandCodeTestRepository;


    @Override
    public void run(String... args) throws Exception {
        List<HollandCodeTestQuestion> seedData = new ArrayList<>();

        //Realistic
        seedData.add(new HollandCodeTestQuestion("Are you practical?", PersonalityType.REALISTIC, "realistic_01"));
        seedData.add(new HollandCodeTestQuestion("Are you straightforward/frank? (frank: honest and direct in what you say)", PersonalityType.REALISTIC, "realistic_02"));
        seedData.add(new HollandCodeTestQuestion("Are you mechanically inclined? (mechanically inclined: naturally good at working with machines and tools)", PersonalityType.REALISTIC, "realistic_03"));
        seedData.add(new HollandCodeTestQuestion("Are you stable?", PersonalityType.REALISTIC, "realistic_04"));
        seedData.add(new HollandCodeTestQuestion("Are you concrete? (concrete: focused on practical, real-world things rather than theories)", PersonalityType.REALISTIC, "realistic_05"));
        seedData.add(new HollandCodeTestQuestion("Are you reserved? (reserved: quiet and not very open or talkative)", PersonalityType.REALISTIC, "realistic_06"));
        seedData.add(new HollandCodeTestQuestion("Are you self-controlled?", PersonalityType.REALISTIC, "realistic_07"));
        seedData.add(new HollandCodeTestQuestion("Are you independent?", PersonalityType.REALISTIC, "realistic_08"));
        seedData.add(new HollandCodeTestQuestion("Are you ambitious?", PersonalityType.REALISTIC, "realistic_09"));
        seedData.add(new HollandCodeTestQuestion("Are you systematic?", PersonalityType.REALISTIC, "realistic_10"));
        seedData.add(new HollandCodeTestQuestion("Can you fix electrical things?", PersonalityType.REALISTIC, "realistic_11"));
        seedData.add(new HollandCodeTestQuestion("Can you solve electrical problems?", PersonalityType.REALISTIC, "realistic_12"));
        seedData.add(new HollandCodeTestQuestion("Can you pitch a tent?", PersonalityType.REALISTIC, "realistic_13"));
        seedData.add(new HollandCodeTestQuestion("Can you play a sport?", PersonalityType.REALISTIC, "realistic_14"));
        seedData.add(new HollandCodeTestQuestion("Can you read a blueprint? (blueprint: a detailed technical drawing or plan)", PersonalityType.REALISTIC, "realistic_15"));
        seedData.add(new HollandCodeTestQuestion("Can you plant a garden?", PersonalityType.REALISTIC, "realistic_16"));
        seedData.add(new HollandCodeTestQuestion("Can you operate tools and machine?", PersonalityType.REALISTIC, "realistic_17"));
        seedData.add(new HollandCodeTestQuestion("Do you like to tinker with machines/vehicles? (tinker: experiment hands-on to fix or improve things)", PersonalityType.REALISTIC, "realistic_18"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work outdoors?", PersonalityType.REALISTIC, "realistic_19"));
        seedData.add(new HollandCodeTestQuestion("Do you like to be physically active?", PersonalityType.REALISTIC, "realistic_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to be use your hands?", PersonalityType.REALISTIC, "realistic_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to build things?", PersonalityType.REALISTIC, "realistic_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to tend/train animals?", PersonalityType.REALISTIC, "realistic_23"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work on electronic equipment?", PersonalityType.REALISTIC, "realistic_24"));


        //Investigative
        seedData.add(new HollandCodeTestQuestion("Are you inquisitive? (inquisitive: always curious and eager to ask questions and find answers)", PersonalityType.INVESTIGATIVE, "investigative_01"));
        seedData.add(new HollandCodeTestQuestion("Are you analytical?", PersonalityType.INVESTIGATIVE, "investigative_02"));
        seedData.add(new HollandCodeTestQuestion("Are you scientific?", PersonalityType.INVESTIGATIVE, "investigative_03"));
        seedData.add(new HollandCodeTestQuestion("Are you observant/precise?", PersonalityType.INVESTIGATIVE, "investigative_04"));
        seedData.add(new HollandCodeTestQuestion("Are you scholarly? (scholarly: dedicated to studying and gaining deep academic knowledge)", PersonalityType.INVESTIGATIVE, "investigative_05"));
        seedData.add(new HollandCodeTestQuestion("Are you cautious?", PersonalityType.INVESTIGATIVE, "investigative_06"));
        seedData.add(new HollandCodeTestQuestion("Are you intellectually self-confident? (intellectually self-confident: trusting your own thinking and knowledge)", PersonalityType.INVESTIGATIVE, "investigative_07"));
        seedData.add(new HollandCodeTestQuestion("Are you independent?", PersonalityType.INVESTIGATIVE, "investigative_08"));
        seedData.add(new HollandCodeTestQuestion("Are you logical?", PersonalityType.INVESTIGATIVE, "investigative_09"));
        seedData.add(new HollandCodeTestQuestion("Are you complex?", PersonalityType.INVESTIGATIVE, "investigative_10"));
        seedData.add(new HollandCodeTestQuestion("Are you curious?", PersonalityType.INVESTIGATIVE, "investigative_11"));
        seedData.add(new HollandCodeTestQuestion("Can you think abstractly? (abstractly: thinking about concepts and ideas rather than just concrete things)", PersonalityType.INVESTIGATIVE, "investigative_12"));
        seedData.add(new HollandCodeTestQuestion("Can you solve math problems?", PersonalityType.INVESTIGATIVE, "investigative_13"));
        seedData.add(new HollandCodeTestQuestion("Can you understand scientific theories?", PersonalityType.INVESTIGATIVE, "investigative_14"));
        seedData.add(new HollandCodeTestQuestion("Can you do complex calculations?", PersonalityType.INVESTIGATIVE, "investigative_15"));
        seedData.add(new HollandCodeTestQuestion("Can you use a microscope or computer?", PersonalityType.INVESTIGATIVE, "investigative_16"));
        seedData.add(new HollandCodeTestQuestion("Can you interpret formulas?", PersonalityType.INVESTIGATIVE, "investigative_17"));
        seedData.add(new HollandCodeTestQuestion("Do you like to explore a variety of ideas?", PersonalityType.INVESTIGATIVE, "investigative_18"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work independently?", PersonalityType.INVESTIGATIVE, "investigative_19"));
        seedData.add(new HollandCodeTestQuestion("Do you like to perform lab experiments?", PersonalityType.INVESTIGATIVE, "investigative_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to deal with abstractions? (abstractions: general concepts or ideas not tied to specific real-world examples)", PersonalityType.INVESTIGATIVE, "investigative_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to do research?", PersonalityType.INVESTIGATIVE, "investigative_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to be challenged?", PersonalityType.INVESTIGATIVE, "investigative_23"));


        //Artistic
        seedData.add(new HollandCodeTestQuestion("Are you creative?", PersonalityType.ARTISTIC, "artistic_01"));
        seedData.add(new HollandCodeTestQuestion("Are you imaginative?", PersonalityType.ARTISTIC, "artistic_02"));
        seedData.add(new HollandCodeTestQuestion("Are you innovative?", PersonalityType.ARTISTIC, "artistic_03"));
        seedData.add(new HollandCodeTestQuestion("Are you unconventional? (unconventional: not following usual norms or typical expectations)", PersonalityType.ARTISTIC, "artistic_04"));
        seedData.add(new HollandCodeTestQuestion("Are you emotional?", PersonalityType.ARTISTIC, "artistic_05"));
        seedData.add(new HollandCodeTestQuestion("Are you independent?", PersonalityType.ARTISTIC, "artistic_06"));
        seedData.add(new HollandCodeTestQuestion("Are you expressive?", PersonalityType.ARTISTIC, "artistic_07"));
        seedData.add(new HollandCodeTestQuestion("Are you original?", PersonalityType.ARTISTIC, "artistic_08"));
        seedData.add(new HollandCodeTestQuestion("Are you introspective? (introspective: reflective about your own thoughts, feelings, and inner life)", PersonalityType.ARTISTIC, "artistic_09"));
        seedData.add(new HollandCodeTestQuestion("Are you impulsive? (impulsive: acting on feelings without thinking things through first)", PersonalityType.ARTISTIC, "artistic_10"));
        seedData.add(new HollandCodeTestQuestion("Are you sensitive?", PersonalityType.ARTISTIC, "artistic_11"));
        seedData.add(new HollandCodeTestQuestion("Are you courageous?", PersonalityType.ARTISTIC, "artistic_12"));
        seedData.add(new HollandCodeTestQuestion("Are you complicated?", PersonalityType.ARTISTIC, "artistic_13"));
        seedData.add(new HollandCodeTestQuestion("Are you idealistic? (idealistic: believing strongly in high ideals, even if they are hard to achieve)", PersonalityType.ARTISTIC, "artistic_14"));
        seedData.add(new HollandCodeTestQuestion("Are you nonconforming? (nonconforming: not following what society typically expects)", PersonalityType.ARTISTIC, "artistic_15"));
        seedData.add(new HollandCodeTestQuestion("Can you sketch, draw, paint?", PersonalityType.ARTISTIC, "artistic_16"));
        seedData.add(new HollandCodeTestQuestion("Can you play a musical instrument?", PersonalityType.ARTISTIC, "artistic_17"));
        seedData.add(new HollandCodeTestQuestion("Can you write stories, poetry, music?", PersonalityType.ARTISTIC, "artistic_18"));
        seedData.add(new HollandCodeTestQuestion("Can you sing, act, dance?", PersonalityType.ARTISTIC, "artistic_19"));
        seedData.add(new HollandCodeTestQuestion("Can you design fashions or interiors?", PersonalityType.ARTISTIC, "artistic_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to attend concerts, theatre, art exhibits?", PersonalityType.ARTISTIC, "artistic_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to attend concerts, theatre, art exhibits?", PersonalityType.ARTISTIC, "artistic_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to read fiction, plays, and poetry?", PersonalityType.ARTISTIC, "artistic_23"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work on crafts?", PersonalityType.ARTISTIC, "artistic_24"));
        seedData.add(new HollandCodeTestQuestion("Do you like to take photography?", PersonalityType.ARTISTIC, "artistic_25"));
        seedData.add(new HollandCodeTestQuestion("Do you like to express yourself creatively?", PersonalityType.ARTISTIC, "artistic_26"));
        seedData.add(new HollandCodeTestQuestion("Do you like to deal with ambiguous ideas? (ambiguous: unclear or open to more than one interpretation)", PersonalityType.ARTISTIC, "artistic_27"));


        //Social
        seedData.add(new HollandCodeTestQuestion("Are you friendly?", PersonalityType.SOCIAL, "social_01"));
        seedData.add(new HollandCodeTestQuestion("Are you helpful?", PersonalityType.SOCIAL, "social_02"));
        seedData.add(new HollandCodeTestQuestion("Are you idealistic? (idealistic: believing strongly in high ideals, even if they are hard to achieve)", PersonalityType.SOCIAL, "social_03"));
        seedData.add(new HollandCodeTestQuestion("Are you insightful? (insightful: able to understand people or situations in a deep, meaningful way)", PersonalityType.SOCIAL, "social_04"));
        seedData.add(new HollandCodeTestQuestion("Are you outgoing?", PersonalityType.SOCIAL, "social_05"));
        seedData.add(new HollandCodeTestQuestion("Are you understanding?", PersonalityType.SOCIAL, "social_06"));
        seedData.add(new HollandCodeTestQuestion("Are you cooperative?", PersonalityType.SOCIAL, "social_07"));
        seedData.add(new HollandCodeTestQuestion("Are you generous?", PersonalityType.SOCIAL, "social_08"));
        seedData.add(new HollandCodeTestQuestion("Are you responsible?", PersonalityType.SOCIAL, "social_09"));
        seedData.add(new HollandCodeTestQuestion("Are you forgiving?", PersonalityType.SOCIAL, "social_10"));
        seedData.add(new HollandCodeTestQuestion("Are you patient?", PersonalityType.SOCIAL, "social_11"));
        seedData.add(new HollandCodeTestQuestion("Are you kind?", PersonalityType.SOCIAL, "social_12"));
        seedData.add(new HollandCodeTestQuestion("Can you teach/train others?", PersonalityType.SOCIAL, "social_13"));
        seedData.add(new HollandCodeTestQuestion("Can you express yourself clearly?", PersonalityType.SOCIAL, "social_14"));
        seedData.add(new HollandCodeTestQuestion("Can you teach/train others?", PersonalityType.SOCIAL, "social_15"));
        seedData.add(new HollandCodeTestQuestion("Can you lead a group discussion?", PersonalityType.SOCIAL, "social_16"));
        seedData.add(new HollandCodeTestQuestion("Can you mediate disputes? (mediate: help two sides reach an agreement; disputes: disagreements or conflicts)", PersonalityType.SOCIAL, "social_17"));
        seedData.add(new HollandCodeTestQuestion("Can you plan and supervise an activity?", PersonalityType.SOCIAL, "social_18"));
        seedData.add(new HollandCodeTestQuestion("Can you cooperate well with others?", PersonalityType.SOCIAL, "social_19"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work in groups?", PersonalityType.SOCIAL, "social_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to help people with problems?", PersonalityType.SOCIAL, "social_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to do volunteer work?", PersonalityType.SOCIAL, "social_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work with young people?", PersonalityType.SOCIAL, "social_23"));
        seedData.add(new HollandCodeTestQuestion("Do you like to serve others?", PersonalityType.SOCIAL, "social_24"));


        //Enterprising
        seedData.add(new HollandCodeTestQuestion("Are you self-confident?", PersonalityType.ENTERPRISING, "enterprising_01"));
        seedData.add(new HollandCodeTestQuestion("Are you assertive? (assertive: confident in expressing your opinions and standing up for yourself)", PersonalityType.ENTERPRISING, "enterprising_02"));
        seedData.add(new HollandCodeTestQuestion("Are you persuasive? (persuasive: able to convince others to agree with you or do something)", PersonalityType.ENTERPRISING, "enterprising_03"));
        seedData.add(new HollandCodeTestQuestion("Are you energetic?", PersonalityType.ENTERPRISING, "enterprising_04"));
        seedData.add(new HollandCodeTestQuestion("Are you adventurous?", PersonalityType.ENTERPRISING, "enterprising_05"));
        seedData.add(new HollandCodeTestQuestion("Are you ambitious?", PersonalityType.ENTERPRISING, "enterprising_06"));
        seedData.add(new HollandCodeTestQuestion("Are you agreeable?", PersonalityType.ENTERPRISING, "enterprising_07"));
        seedData.add(new HollandCodeTestQuestion("Are you talkative?", PersonalityType.ENTERPRISING, "enterprising_08"));
        seedData.add(new HollandCodeTestQuestion("Are you extroverted? (extroverted: outgoing and energized by being around and interacting with others)", PersonalityType.ENTERPRISING, "enterprising_09"));
        seedData.add(new HollandCodeTestQuestion("Are you spontaneous? (spontaneous: acting on impulse, without prior planning)", PersonalityType.ENTERPRISING, "enterprising_10"));
        seedData.add(new HollandCodeTestQuestion("Are you optimistic?", PersonalityType.ENTERPRISING, "enterprising_11"));
        seedData.add(new HollandCodeTestQuestion("Can you initiate projects? (initiate: start or begin something new)", PersonalityType.ENTERPRISING, "enterprising_12"));
        seedData.add(new HollandCodeTestQuestion("Can you convince people to do things your way?", PersonalityType.ENTERPRISING, "enterprising_13"));
        seedData.add(new HollandCodeTestQuestion("Can you sell things?", PersonalityType.ENTERPRISING, "enterprising_14"));
        seedData.add(new HollandCodeTestQuestion("Can you give talks or speeches?", PersonalityType.ENTERPRISING, "enterprising_15"));
        seedData.add(new HollandCodeTestQuestion("Can you organize activities?", PersonalityType.ENTERPRISING, "enterprising_16"));
        seedData.add(new HollandCodeTestQuestion("Can you lead a group?", PersonalityType.ENTERPRISING, "enterprising_17"));
        seedData.add(new HollandCodeTestQuestion("Can you persuade others?", PersonalityType.ENTERPRISING, "enterprising_18"));
        seedData.add(new HollandCodeTestQuestion("Do you like to make decisions?", PersonalityType.ENTERPRISING, "enterprising_19"));
        seedData.add(new HollandCodeTestQuestion("Do you like to be elected to office? (elected to office: chosen by vote for a leadership or official position)", PersonalityType.ENTERPRISING, "enterprising_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to start your own business?", PersonalityType.ENTERPRISING, "enterprising_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to campaign politically? (campaign politically: actively work to get yourself or a candidate elected)", PersonalityType.ENTERPRISING, "enterprising_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to meet important people?", PersonalityType.ENTERPRISING, "enterprising_23"));
        seedData.add(new HollandCodeTestQuestion("Do you like to have power or status?", PersonalityType.ENTERPRISING, "enterprising_24"));



        //Conventional
        seedData.add(new HollandCodeTestQuestion("Are you well-organized?", PersonalityType.CONVENTIONAL, "conventional_01"));
        seedData.add(new HollandCodeTestQuestion("Are you accurate?", PersonalityType.CONVENTIONAL, "conventional_02"));
        seedData.add(new HollandCodeTestQuestion("Are you numerically inclined? (numerically inclined: naturally comfortable and good at working with numbers)", PersonalityType.CONVENTIONAL, "conventional_03"));
        seedData.add(new HollandCodeTestQuestion("Are you methodical? (methodical: following a careful, step-by-step plan to do things)", PersonalityType.CONVENTIONAL, "conventional_04"));
        seedData.add(new HollandCodeTestQuestion("Are you conscientious? (conscientious: careful, thorough, and always trying to do the right thing)", PersonalityType.CONVENTIONAL, "conventional_05"));
        seedData.add(new HollandCodeTestQuestion("Are you efficient?", PersonalityType.CONVENTIONAL, "conventional_06"));
        seedData.add(new HollandCodeTestQuestion("Are you conforming? (conforming: following rules and meeting the expectations of others)", PersonalityType.CONVENTIONAL, "conventional_07"));
        seedData.add(new HollandCodeTestQuestion("Are you practical?", PersonalityType.CONVENTIONAL, "conventional_08"));
        seedData.add(new HollandCodeTestQuestion("Are you thrifty? (thrifty: careful with money and not wasteful)", PersonalityType.CONVENTIONAL, "conventional_09"));
        seedData.add(new HollandCodeTestQuestion("Are you systematic?", PersonalityType.CONVENTIONAL, "conventional_10"));
        seedData.add(new HollandCodeTestQuestion("Are you structured?", PersonalityType.CONVENTIONAL, "conventional_11"));
        seedData.add(new HollandCodeTestQuestion("Are you polite?", PersonalityType.CONVENTIONAL, "conventional_12"));
        seedData.add(new HollandCodeTestQuestion("Are you ambitious?", PersonalityType.CONVENTIONAL, "conventional_13"));
        seedData.add(new HollandCodeTestQuestion("Are you obedient?", PersonalityType.CONVENTIONAL, "conventional_14"));
        seedData.add(new HollandCodeTestQuestion("Are you persistent?", PersonalityType.CONVENTIONAL, "conventional_15"));
        seedData.add(new HollandCodeTestQuestion("Can you work well within a system?", PersonalityType.CONVENTIONAL, "conventional_16"));
        seedData.add(new HollandCodeTestQuestion("Can you do a lot of paper work in a short time?", PersonalityType.CONVENTIONAL, "conventional_17"));
        seedData.add(new HollandCodeTestQuestion("Can you keep accurate records?", PersonalityType.CONVENTIONAL, "conventional_18"));
        seedData.add(new HollandCodeTestQuestion("Can you use a computer terminal?", PersonalityType.CONVENTIONAL, "conventional_19"));
        seedData.add(new HollandCodeTestQuestion("Can you write effective business letters?", PersonalityType.CONVENTIONAL, "conventional_20"));
        seedData.add(new HollandCodeTestQuestion("Do you like to follow clearly defined procedures?", PersonalityType.CONVENTIONAL, "conventional_21"));
        seedData.add(new HollandCodeTestQuestion("Do you like to use data processing equipment?", PersonalityType.CONVENTIONAL, "conventional_22"));
        seedData.add(new HollandCodeTestQuestion("Do you like to work with numbers?", PersonalityType.CONVENTIONAL, "conventional_23"));
        seedData.add(new HollandCodeTestQuestion("Do you like to type or take shorthand? (shorthand: a fast writing method using abbreviations and symbols)", PersonalityType.CONVENTIONAL, "conventional_24"));
        seedData.add(new HollandCodeTestQuestion("Do you like to be responsible for details?", PersonalityType.CONVENTIONAL, "conventional_25"));
        seedData.add(new HollandCodeTestQuestion("Do you like to collect or organize things?", PersonalityType.CONVENTIONAL, "conventional_26"));


        List<HollandCodeTestQuestion> persistedQuestions = new ArrayList<>();
        for (HollandCodeTestQuestion q : seedData) {
            HollandCodeTestQuestion persisted = hollandCodeTestQuestionRepository
                    .findByQuestionKey(q.getQuestionKey())
                    .orElseGet(() -> hollandCodeTestQuestionRepository.save(q));
            persistedQuestions.add(persisted);
        }

        List<HollandCodeTestQuestion> subSet = new ArrayList<>();
        for (int i = 0; i < persistedQuestions.size(); i++) {
            if ((i + 1) % 10 == 0) { // Check if it's the 10th question
                subSet.add(persistedQuestions.get(i));
            }
        }

        if (!hollandCodeTestRepository.findByTitle("Holland Code Test (Demo)").isPresent()) {
            HollandCodeTest testDemonstration = new HollandCodeTest("Holland Code Test (Demo)", "This is a personality test based on the RIASEC model. This test is for demonstration purposes only, with a limited amount of questions.", 1, subSet);
            hollandCodeTestRepository.save(testDemonstration);
        }

        if (!hollandCodeTestRepository.findByTitle("Holland Code Test").isPresent()) {
            HollandCodeTest fullTest = new HollandCodeTest("Holland Code Test", "This is a personality test based on the RIASEC model. This is a full version of the test, including every question.", 1, persistedQuestions);
            hollandCodeTestRepository.save(fullTest);
        }

    }
}
