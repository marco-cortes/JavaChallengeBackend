package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    // @Query("select m from Movie m where m.id = ( select mp.movie.id from MoviePersonage mp where mp.personage.id = ?1 group by mp.personage.id )")
    // List<Movie> getMoviesByPersonageId(Long idPersonage);
}
