package riasec.backend.repository;

import org.springframework.data.repository.CrudRepository;
import riasec.backend.model.classes.HollandCodeTestQuestion;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called hollandCodeTestQuestionRepository
// CRUD refers Create, Read, Update, Delete
public interface HollandCodeTestQuestionRepository extends CrudRepository<HollandCodeTestQuestion, Integer> {
    Optional<HollandCodeTestQuestion> findByQuestionKey(String questionKey);
}
