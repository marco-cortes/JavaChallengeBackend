package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> getMoviesByTitle(String title);
    List<Movie> getMoviesByGenre_Id(Long id);
}
