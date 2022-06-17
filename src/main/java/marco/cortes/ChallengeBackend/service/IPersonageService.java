package marco.cortes.ChallengeBackend.service;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.dto.PersonageDetails;
import marco.cortes.ChallengeBackend.dto.PersonageInfo;
import marco.cortes.ChallengeBackend.entity.Movie;
import marco.cortes.ChallengeBackend.entity.Personage;
import marco.cortes.ChallengeBackend.repo.MovieRepo;
import marco.cortes.ChallengeBackend.repo.PersonageRepo;
import marco.cortes.ChallengeBackend.util.Util;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IPersonageService implements PersonageService {

    private final PersonageRepo personageRepo;
    private final MovieRepo movieRepo;

    @Override
    public Personage save(Personage personage) {
        //Save a new Personage
        return personageRepo.save(personage);
    }

    @Override
    public PersonageDetails findById(Long id) {
        //Find Personage by id
        Personage p = personageRepo.findById(id).orElse(null);
        if(p == null)
            return null;

        PersonageDetails pd = new PersonageDetails();
        pd.setId(p.getId());
        pd.setImage(p.getImage());
        pd.setAge(p.getAge());
        pd.setHistory(p.getHistory());
        pd.setName(p.getName());
        pd.setWeight(p.getWeight());
        pd.setMovies(Util.movieInfo(movieRepo.getMoviesByUser_Id(p.getId())));

        return pd;
    }

    @Override
    public List<PersonageInfo> search(String parameter, String value) {
        //Search Personages by parameter and value

        //If parameter is none, return all Personages
        if(parameter.equals("none"))
            return Util.personageInfo(personageRepo.findAll());
        else if(parameter.equals("name"))
            return Util.personageInfo(personageRepo.findAllByName(value));
        else if(parameter.equals("age"))
            return Util.personageInfo(personageRepo.findAllByAge(Integer.parseInt(value)));
        else {
            Movie movie = movieRepo.findById(Long.parseLong(value)).orElse(null);
            if(movie == null)
                return new ArrayList<>();
            return Util.personageInfo(movie.getPersonages());
        }
    }

    @Override
    public Personage update(Personage personage, Long id) {
        //Personage update

        //Search old personage by id
        Personage old = personageRepo.findById(id).orElse(null);

        //If old personage isn't in database, return null
        if(old == null)
            return null;

        //If old personage id is not be the same as new personage id, return null
        if(!old.getId().equals(personage.getId()))
            return null;

        //Validations for values of object type Personage

        if(Util.updateValidation(personage.getAge(), old.getAge()))
            old.setAge(personage.getAge());

        if(Util.updateValidation(personage.getHistory(), old.getHistory()))
            old.setHistory(personage.getHistory());

        if(Util.updateValidation(personage.getImage(), old.getImage()))
            old.setImage(personage.getImage());

        if(Util.updateValidation(personage.getName(), old.getName()))
            old.setName(personage.getName());

        if(Util.updateValidation(personage.getWeight(), old.getWeight()))
            old.setWeight(personage.getWeight());

        //update old Personage
        return personageRepo.save(old);
    }

    @Override
    public Personage delete(Long id) {
        //Find personage by id
        Personage personage = personageRepo.findById(id).orElse(null);

        //if personage is not in database, return null
        if(personage == null)
            return null;

        //if personage exists in any movie, delete this relationship
        movieRepo.deleteMoviesPersonages(id);

        //if personage is in database, will be deleted
        personageRepo.delete(personage);
        return personage;
    }
}
