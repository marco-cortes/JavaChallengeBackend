package marco.cortes.ChallengeBackend.service;

import marco.cortes.ChallengeBackend.entity.User;

public interface UserService {
    User save(User user);
    User getUserByEmail(String email);
}
