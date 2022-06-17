package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.Genre;
import marco.cortes.ChallengeBackend.entity.Movie;
import marco.cortes.ChallengeBackend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("")
    public ResponseEntity<?> all(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String genre,
                                 @RequestParam(required = false) String order) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            if(name != null) {
                data.put("characters", movieService.search("name", name));
                return ResponseEntity.ok(data);
            } else if (genre != null) {
                data.put("characters", movieService.search("genre", genre));
                return ResponseEntity.ok(data);
            } else if(order != null) {
                data.put("characters", movieService.search("order", order));
                return ResponseEntity.ok(data);
            }
            data.put("characters", movieService.search("none", null));
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
            Movie movie = movieService.findById(id);
            if(movie == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Movie not found");
                return ResponseEntity.badRequest().body(data);
            }
            data.put("ok", Boolean.TRUE);
            data.put("movie", movie);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Movie movie) {
        Map<String, Object> data = new HashMap<>();
        try {
            Movie m = movieService.save(movie);
            if(m == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Score is not valid");
                return ResponseEntity.badRequest().body(data);
            }

            data.put("ok", Boolean.TRUE);
            data.put("movie", m);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@RequestBody Movie movie, @PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Movie newMovie = movieService.update(movie, id);
            if(newMovie == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Movie not found");
                return ResponseEntity.badRequest().body(data);
            }
            data.put("ok", Boolean.TRUE);
            data.put("movie", newMovie);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Movie movie = movieService. delete(id);
            if(movie == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Movie not found");
                return ResponseEntity.badRequest().body(data);
            }
            data.put("ok", Boolean.TRUE);
            data.put("movie", movie);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        Map<String, Object> data = new HashMap<>();
        try {
            Movie m = movieService.addPersonage(idMovie, idCharacter);
            if(m == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Saving error");
                return ResponseEntity.badRequest().body(data);
            }
            data.put("ok", Boolean.TRUE);
            data.put("movie_character", m);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        Map<String, Object> data = new HashMap<>();
        try {
            Movie m = movieService.removePersonage(idMovie, idCharacter);
            if(m == null) {
                data.put("ok", Boolean.FALSE);
                data.put("message", "Saving error");
                return ResponseEntity.badRequest().body(data);
            }
            data.put("ok", Boolean.TRUE);
            data.put("movie_character", m);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/genre/add")
    public ResponseEntity<?> addGenre(@RequestBody Genre genre) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            data.put("genre", movieService.add(genre));
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }
}