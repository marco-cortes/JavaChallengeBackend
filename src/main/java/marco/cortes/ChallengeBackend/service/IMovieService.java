package marco.cortes.ChallengeBackend.service;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.*;
import marco.cortes.ChallengeBackend.repo.GenreRepo;
import marco.cortes.ChallengeBackend.repo.MoviePersonageRepo;
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
public class IMovieService implements MovieService{

    private final MovieRepo movieRepo;
    private final PersonageRepo personageRepo;
    private final MoviePersonageRepo moviePersonageRepo;
    private final GenreRepo genreRepo;
    @Override
    public Movie save(Movie movie) {
        movie.setCreatedAt(new Date(new java.util.Date().getTime()));
        return movieRepo.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepo.findById(id).orElse(null);
    }

    @Override
    public List<Movie> search(String parameter, String value) {
        switch (parameter) {
            case "none": default:
                return movieRepo.findAll();
            case "name":
                return movieRepo.getMoviesByTitle(value);
            case "genre":
                return movieRepo.getMoviesByGenre_Id(Long.parseLong(value));
            case "order":
                if(value.equals("ASC"))
                    return movieRepo.findAll(Sort.by(Sort.Direction.ASC, "title"));
                else if(value.equals("DESC"))
                    return movieRepo.findAll(Sort.by(Sort.Direction.DESC, "title"));
                else
                    return movieRepo.findAll();
        }
    }

    @Override
    public MoviePersonage addPersonage(Long idMovie, Long idPersonage) {
        Movie m = findById(idMovie);

        if(m == null)
            return null;

        Personage p = personageRepo.findById(idPersonage).orElse(null);

        if(p == null)
            return null;

        MoviePersonageFK pk = new MoviePersonageFK(idPersonage, idMovie);
        MoviePersonage mp = new MoviePersonage();
        mp.setId(pk);
        mp.setMovie(m);
        mp.setPersonage(p);

        return moviePersonageRepo.save(mp);
    }

    @Override
    public MoviePersonage removePersonage(Long idMovie, Long idPersonage) {
        Movie m = findById(idMovie);

        if(m == null)
            return null;

        Personage p = personageRepo.findById(idPersonage).orElse(null);

        if(p == null)
            return null;

        MoviePersonageFK pk = new MoviePersonageFK(idPersonage, idMovie);
        MoviePersonage mp = new MoviePersonage();
        mp.setId(pk);
        mp.setMovie(m);
        mp.setPersonage(p);
        moviePersonageRepo.delete(mp);
        return mp;
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
        else if(Util.updateValidation(movie.getScore(), old.getImage()))
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
        movieRepo.delete(m);
        return m;
    }
}
