package marco.cortes.ChallengeBackend.service;

import marco.cortes.ChallengeBackend.dto.PersonageInfo;
import marco.cortes.ChallengeBackend.entity.Personage;

import java.util.List;

public interface PersonageService {
    Personage save(Personage personage);
    Personage findById(Long id);
    List<PersonageInfo> search(String parameter, Object value);
    Personage update(Personage personage, Long id);
    Personage delete(Long id);
}
