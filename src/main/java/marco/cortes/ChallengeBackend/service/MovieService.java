package marco.cortes.ChallengeBackend.service;

import marco.cortes.ChallengeBackend.entity.Movie;
import marco.cortes.ChallengeBackend.entity.MoviePersonage;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    Movie findById(Long id);
    List<Movie> search(String parameter, String value);
    MoviePersonage addPersonage(Long idMovie, Long idPersonage);
    MoviePersonage removePersonage(Long idMovie, Long idPersonage);
    Movie update(Movie movie, Long id);
    Movie delete(Long id);
}
