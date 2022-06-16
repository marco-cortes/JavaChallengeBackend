package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
