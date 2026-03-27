package riasec.backend.repository;

import org.springframework.data.repository.CrudRepository;
import riasec.backend.model.classes.Profession;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called testTakerRepository
// CRUD refers Create, Read, Update, Delete
public interface ProfessionRepository extends CrudRepository<Profession, Integer> {
    List<Profession> findByTitleAndHollandCode(String title, String hollandCode);
}
