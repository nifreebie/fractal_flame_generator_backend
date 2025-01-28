package nifreebie.fractal_flame_generator_backend.service.generator;


import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Rect;

@FunctionalInterface
public interface Generator {
    FractalImage render(int width, int height, Rect world);
}
