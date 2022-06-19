package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.Genre;
import marco.cortes.ChallengeBackend.entity.Movie;
import marco.cortes.ChallengeBackend.service.MovieService;
import marco.cortes.ChallengeBackend.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("")
    public ResponseEntity<?> getMovies(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String genre,
                                 @RequestParam(required = false) String order) {
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("ok", Boolean.TRUE);
            if(name != null) {
                data.put("movies", movieService.search("name", name));
                return ResponseEntity.ok(data);
            } else if (genre != null) {
                data.put("movies", movieService.search("genre", genre));
                return ResponseEntity.ok(data);
            } else if(order != null) {
                data.put("movies", movieService.search("order", order));
                return ResponseEntity.ok(data);
            }
            data.put("movies", movieService.search("none", null));
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            data.put("ok", Boolean.FALSE);
            data.put("message", "Error in server");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> movieById(@PathVariable @NotNull(message = "Movie id is required") Long id) {
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
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
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
    public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie, @PathVariable @NotNull(message = "Movie id is required") Long id) {
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
    public ResponseEntity<?> deleteMovie(@PathVariable @NotNull(message = "Movie id is required") Long id) {
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
    public ResponseEntity<?> addCharacter(
            @PathVariable @NotNull(message = "Movie id is required") Long idMovie,
            @PathVariable @NotNull(message = "Character id is required") Long idCharacter) {
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
    public ResponseEntity<?> deleteCharacter(@PathVariable @NotNull(message = "Movie id is required") Long idMovie,
                                             @PathVariable @NotNull(message = "Movie id is required") Long idCharacter) {
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
    public ResponseEntity<?> addGenre(@Valid @RequestBody Genre genre) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return Util.errors(ex);
    }
}
