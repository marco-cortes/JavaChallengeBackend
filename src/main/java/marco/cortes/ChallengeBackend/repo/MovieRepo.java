package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> getMoviesByTitle(String title);
    List<Movie> getMoviesByGenre_Id(Long id);


    @Modifying
    @Query(value = "DELETE FROM movie_personages WHERE personages_id = ?1", nativeQuery = true)
    void deleteMoviesPersonages(Long id);

    @Query(value = "SELECT * FROM movie WHERE id IN (SELECT movie_id FROM movie_personages WHERE personages_id = ?1)", nativeQuery = true)
    List<Movie> getMoviesByUser_Id(Long id);
}
