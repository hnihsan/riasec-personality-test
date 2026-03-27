package riasec.backend.repository;

import org.springframework.data.repository.CrudRepository;
import riasec.backend.model.classes.HollandCodeTest;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called hollandCodeTestRepository
// CRUD refers Create, Read, Update, Delete
public interface HollandCodeTestRepository extends CrudRepository<HollandCodeTest, Integer> {
    Optional<HollandCodeTest> findByTitle(String title);
}
