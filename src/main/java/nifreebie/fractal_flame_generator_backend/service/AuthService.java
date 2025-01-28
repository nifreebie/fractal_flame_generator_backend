package nifreebie.fractal_flame_generator_backend.service;

import java.util.UUID;

public interface AuthService {
    UUID signUp(String username, String password, String email);

    UUID signIn(String username, String password);

}
