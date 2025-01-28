package nifreebie.fractal_flame_generator_backend.service;

import nifreebie.fractal_flame_generator_backend.domain.Image;
import nifreebie.fractal_flame_generator_backend.model.transformations.Transformation;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ImageService {
    String generateImage(int width,
                                int height,
                                int iterations,
                                int symmetry,
                                List<Transformation> transformations,
                                UUID user_id) throws IOException, Exception;
    List<Image> getUserImages(UUID userId);
}
