package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.dto.PersonageDetails;
import marco.cortes.ChallengeBackend.entity.Personage;
import marco.cortes.ChallengeBackend.service.PersonageService;
import marco.cortes.ChallengeBackend.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class PersonageController {

    private final PersonageService personageService;

    @GetMapping("")
    public ResponseEntity<?> getCharacters(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String age,
                                 @RequestParam(required = false) String movie) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            if(name != null) {
                data.put("characters", personageService.search("name", name));
                return ResponseEntity.ok(data);
            } else if (age != null) {
                data.put("characters", personageService.search("age", age));
                return ResponseEntity.ok(data);
            } else if(movie != null) {
                data.put("characters", personageService.search("movies", movie));
                return ResponseEntity.ok(data);
            }
            data.put("characters", personageService.search("none", null));
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> characterById(@PathVariable @NotNull(message = "Character id is required") Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            PersonageDetails p = personageService.findById(id);
            if(p == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Character not found");
                return ResponseEntity.badRequest().body(data);
            }

            data.put("ok", Boolean.TRUE);
            data.put("character", p);
            return ResponseEntity.ok(data);

        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCharacter(@Valid @RequestBody Personage personage) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            data.put("character", personageService.save(personage));
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Saving error");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateCharacter(@RequestBody Personage personage, @PathVariable @NotNull(message = "Character id is required") Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Personage p = personageService.update(personage, id);

            if(p == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Character not found");
                return ResponseEntity.badRequest().body(data);
            }

            data.put("ok", Boolean.TRUE);
            data.put("character", p);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Updating error");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteCharacter(@PathVariable @NotNull(message = "Character id is required") Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Personage p = personageService.delete(id);

            if(p == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Character not found");
                return ResponseEntity.badRequest().body(data);
            }

            data.put("ok", Boolean.TRUE);
            data.put("character", p);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Deleting error");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return Util.errors(ex);
    }
}
