package nifreebie.fractal_flame_generator_backend.service.Impl;

import lombok.RequiredArgsConstructor;
import nifreebie.fractal_flame_generator_backend.domain.User;
import nifreebie.fractal_flame_generator_backend.exception.DuplicateUsernameException;
import nifreebie.fractal_flame_generator_backend.exception.IncorrectUsernameOrPasswordException;
import nifreebie.fractal_flame_generator_backend.repository.UserRepository;
import nifreebie.fractal_flame_generator_backend.service.AuthService;
import nifreebie.fractal_flame_generator_backend.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UUID signUp(String username, String password) {
        if(userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Username already exists");
        } else {

            return userRepository.save(
                    User.builder()
                    .username(username)
                    .password(PasswordUtil.hashPassword(password))
                    .build())
                    .getId();
        }
    }

    @Override
    public UUID signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null || !PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new IncorrectUsernameOrPasswordException("Incorrect username or password");
        }
        return user.getId();
    }
}
