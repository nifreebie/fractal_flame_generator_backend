package nifreebie.fractal_flame_generator_backend.repository;

import nifreebie.fractal_flame_generator_backend.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends CrudRepository<Image, UUID> {
    List<Image> findByUserId(UUID userId);
}
