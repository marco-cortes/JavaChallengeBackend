package marco.cortes.ChallengeBackend.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Component
public class Keys {
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.start}")
    private String start;
    @Value("${security.jwt.jwtDuration}")
    private int jwtDuration;
    @Value("${send.grid.api.key}")
    private String sendgridApiKey;
    @Value("${send.grid.email}")
    private String sendgridEmail;
    @Value("${app.url}")
    private String appUrl;
}
