package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.MoviePersonage;
import marco.cortes.ChallengeBackend.entity.MoviePersonageFK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviePersonageRepo extends JpaRepository<MoviePersonage, MoviePersonageFK> {

    List<MoviePersonage> getMoviePersonagesByPersonage_Id(Long personageId);
}
