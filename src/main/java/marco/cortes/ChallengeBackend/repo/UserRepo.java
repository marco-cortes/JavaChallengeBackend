package marco.cortes.ChallengeBackend.repo;

import marco.cortes.ChallengeBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
