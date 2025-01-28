package nifreebie.fractal_flame_generator_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import nifreebie.fractal_flame_generator_backend.model.transformations.TransformationType;

import java.util.List;

@Data
@AllArgsConstructor
public class GenerateImageRequest {
    private int width;
    private int height;
    private int iterations;
    private int symmetry;
    private List<TransformationType> transformations;
}
