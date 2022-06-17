package marco.cortes.ChallengeBackend.service;

import com.sendgrid.*;
import lombok.RequiredArgsConstructor;
import marco.cortes.ChallengeBackend.entity.User;
import marco.cortes.ChallengeBackend.repo.UserRepo;
import marco.cortes.ChallengeBackend.util.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class IUserService implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final Keys keys;

    @Override
    public User save(User user) {
        try {
            Email from = new Email(keys.getSendgridEmail());
            String subject = "Java Backend Challenge";
            Email to = new Email(user.getEmail());
            Content content;
            content = new Content("text/plain", "Marco says: Welcome to my API! :)");
            Mail mail = new Mail(from, subject, to, content);
            SendGrid sg = new SendGrid(keys.getSendgridApiKey());
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                sg.api(request);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
