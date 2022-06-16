package marco.cortes.ChallengeBackend.service;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.dto.PersonageInfo;
import marco.cortes.ChallengeBackend.entity.Personage;
import marco.cortes.ChallengeBackend.repo.MovieRepo;
import marco.cortes.ChallengeBackend.repo.PersonageRepo;
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
    public Personage findById(Long id) {
        //Find Personage by id
        return personageRepo.findById(id).orElse(null);
    }

    @Override
    public List<PersonageInfo> search(String parameter, Object value) {
        //Search Personages by parameter and value
        System.out.println(parameter);
        System.out.println(value);
        //If parameter is none, return all Personages
        if(parameter.equals("none"))
            return personageInfo(personageRepo.findAll());
        return personageInfo(personageRepo.search(parameter, value));
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

        if(updateValidation(personage.getAge(), old.getAge()))
            old.setAge(personage.getAge());

        if(updateValidation(personage.getHistory(), old.getHistory()))
            old.setHistory(personage.getHistory());

        if(updateValidation(personage.getImage(), old.getImage()))
            old.setImage(personage.getImage());

        if(updateValidation(personage.getName(), old.getName()))
            old.setName(personage.getName());

        if(updateValidation(personage.getWeight(), old.getWeight()))
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
        //if personage is in database, will be deleted
        personageRepo.delete(personage);
        return personage;
    }

    //If new value is null or equal as old value, current value will not be updated
    public Boolean updateValidation(Object newValue, Object oldValue) {
        if(newValue == null)
            return Boolean.FALSE;
        else if(oldValue.equals(newValue))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public List<PersonageInfo> personageInfo(List<Personage> personages) {
        List<PersonageInfo> infoList = new ArrayList<>();
        PersonageInfo aux = new PersonageInfo();
        for(Personage p: personages) {
            aux.setId(p.getId());
            aux.setImage(p.getImage());
            aux.setName(p.getName());
            infoList.add(aux);
            aux = new PersonageInfo();
        }
        return infoList;
    }
}
