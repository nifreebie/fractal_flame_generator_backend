package nifreebie.fractal_flame_generator_backend.repository;

import nifreebie.fractal_flame_generator_backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User getById(UUID uuid);
}
