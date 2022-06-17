package marco.cortes.ChallengeBackend.util;

import marco.cortes.ChallengeBackend.dto.PersonageInfo;
import marco.cortes.ChallengeBackend.entity.Personage;

import java.util.ArrayList;
import java.util.List;

public class Util {
    //If new value is null or equal as old value, current value will not be updated
    public static Boolean updateValidation(Object newValue, Object oldValue) {
        if(newValue == null)
            return Boolean.FALSE;
        else if(oldValue.equals(newValue))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public static List<PersonageInfo> personageInfo(List<Personage> personages) {
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
