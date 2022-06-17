package marco.cortes.ChallengeBackend.controller;

import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.User;
import marco.cortes.ChallengeBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if(userService.getUserByEmail(user.getEmail()) != null) {
            Map<String, String> status = new HashMap<>();
            status.put("error", "El correo ya está registrado.");
            return ResponseEntity.badRequest().body(status);
        }
        return ResponseEntity.ok(userService.save(user));
    }

}
