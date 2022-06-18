package marco.cortes.ChallengeBackend.util;

import marco.cortes.ChallengeBackend.dto.MovieInfo;
import marco.cortes.ChallengeBackend.dto.PersonageInfo;
import marco.cortes.ChallengeBackend.entity.Movie;
import marco.cortes.ChallengeBackend.entity.Personage;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<MovieInfo> movieInfo(List<Movie> movies) {
        List<MovieInfo> movieList = new ArrayList<>();
        MovieInfo aux = new MovieInfo();
        for(Movie m: movies) {
            aux.setId(m.getId());
            aux.setImage(m.getImage());
            aux.setTitle(m.getTitle());
            aux.setCreatedAt(m.getCreatedAt());
            movieList.add(aux);
            aux = new MovieInfo();
        }
        return movieList;
    }

    public static Map<String, Object> errors (MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("ok", Boolean.FALSE);
        return errors;
    }
}
