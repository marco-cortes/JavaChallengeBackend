package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonageRepo extends JpaRepository<Personage, Long> {
    @Query("SELECT p from Personage p where ?1 = ?2")
    List<Personage> search(String parameter, Object value);

    List<Personage> findAllByName(String name);
    List<Personage> findAllByAge(Integer age);

}
