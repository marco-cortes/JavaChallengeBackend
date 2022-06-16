package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.Personage;
import marco.cortes.ChallengeBackend.service.PersonageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class PersonageController {

    private final PersonageService personageService;

    @GetMapping("")
    public ResponseEntity<?> all(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Integer age,
                                        @RequestParam(required = false) Long movies) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            if(name != null) {
                data.put("characters", personageService.search("name", name));
                return ResponseEntity.ok(data);
            } else if (age != null) {
                data.put("characters", personageService.search("age", age));
                return ResponseEntity.ok(data);
            } else if(movies != null) {
                data.put("characters", personageService.search("movies", movies));
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
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Personage p = personageService.findById(id);

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
    public ResponseEntity<?> add(@RequestBody Personage personage) {
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
    public ResponseEntity<?> update(@RequestBody Personage personage, @PathVariable Long id) {
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
    public ResponseEntity<?> delete(@PathVariable Long id) {
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
}
