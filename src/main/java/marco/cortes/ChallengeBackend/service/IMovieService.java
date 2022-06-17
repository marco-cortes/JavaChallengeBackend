package marco.cortes.ChallengeBackend.service;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.dto.MovieInfo;
import marco.cortes.ChallengeBackend.entity.*;
import marco.cortes.ChallengeBackend.repo.GenreRepo;
import marco.cortes.ChallengeBackend.repo.MovieRepo;
import marco.cortes.ChallengeBackend.repo.PersonageRepo;
import marco.cortes.ChallengeBackend.util.Util;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IMovieService implements MovieService {

    private final MovieRepo movieRepo;
    private final PersonageRepo personageRepo;
    private final GenreRepo genreRepo;
    @Override
    public Movie save(Movie movie) {
        movie.setCreatedAt(new Date(new java.util.Date().getTime()));
        if(!(movie.getScore() >= 1 && movie.getScore() <= 5))
            return null;
        return movieRepo.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepo.findById(id).orElse(null);
    }

    @Override
    public List<MovieInfo> search(String parameter, String value) {
        switch (parameter) {
            case "none": default:
                return Util.movieInfo(movieRepo.findAll());
            case "name":
                return Util.movieInfo(movieRepo.getMoviesByTitle(value));
            case "genre":
                return Util.movieInfo(movieRepo.getMoviesByGenre_Id(Long.parseLong(value)));
            case "order":
                if(value.equals("ASC"))
                    return Util.movieInfo(movieRepo.findAll(Sort.by(Sort.Direction.ASC, "title")));
                else if(value.equals("DESC"))
                    return Util.movieInfo(movieRepo.findAll(Sort.by(Sort.Direction.DESC, "title")));
                else
                    return Util.movieInfo(movieRepo.findAll());
        }
    }

    @Override
    public Movie addPersonage(Long idMovie, Long idPersonage) {
        Movie m = findById(idMovie);

        if(m == null)
            return null;

        Personage p = personageRepo.findById(idPersonage).orElse(null);

        if(p == null)
            return null;

        m.getPersonages().add(p);
        m = movieRepo.save(m);
        return m;
    }

    @Override
    public Movie removePersonage(Long idMovie, Long idPersonage) {
        Movie m = findById(idMovie);

        if(m == null)
            return null;

        Personage p = personageRepo.findById(idPersonage).orElse(null);

        if(p == null)
            return null;


        m.getPersonages().remove(p);
        m = movieRepo.save(m);
        return m;
    }

    @Override
    public Movie update(Movie movie, Long id) {

        Movie old = findById(id);
        if(old == null)
            return null;

        if(!movie.getId().equals(id))
            return  null;

        if(Util.updateValidation(movie.getTitle(), old.getTitle()))
            old.setTitle(movie.getTitle());
        else if(Util.updateValidation(movie.getImage(), old.getImage()))
            old.setImage(movie.getImage());
        else if(Util.updateValidation(movie.getScore(), old.getImage()) && movie.getScore() >= 1 && movie.getScore() <= 5)
            old.setScore(movie.getScore());
        else if(Util.updateValidation(movie.getGenre().getId(), old.getGenre().getId())) {
            old.setGenre(genreRepo.findById(movie.getGenre().getId()).orElse(null));
        }
        return movieRepo.save(old);
    }

    @Override
    public Movie delete(Long id) {
        Movie m = findById(id);
        if(m == null)
            return null;

        m.getPersonages().clear();
        movieRepo.save(m);
        movieRepo.delete(m);
        return m;
    }

    @Override
    public Genre add(Genre genre) {
        return genreRepo.save(genre);
    }
}
