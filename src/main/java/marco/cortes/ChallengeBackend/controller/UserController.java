package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.User;
import marco.cortes.ChallengeBackend.service.UserService;
import marco.cortes.ChallengeBackend.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if(userService.getUserByEmail(user.getEmail()) != null) {
            Map<String, Object> status = new HashMap<>();
            status.put("error", "Email already registered.");
            status.put("ok", Boolean.FALSE);
            return ResponseEntity.badRequest().body(status);
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return Util.errors(ex);
    }

}
