package marco.cortes.ChallengeBackend.service;

import marco.cortes.ChallengeBackend.dto.MovieInfo;
import marco.cortes.ChallengeBackend.entity.Genre;
import marco.cortes.ChallengeBackend.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    Movie findById(Long id);
    List<MovieInfo> search(String parameter, String value);
    Movie addPersonage(Long idMovie, Long idPersonage);
    Movie removePersonage(Long idMovie, Long idPersonage);
    Movie update(Movie movie, Long id);
    Movie delete(Long id);

    Genre add(Genre genre);
}
