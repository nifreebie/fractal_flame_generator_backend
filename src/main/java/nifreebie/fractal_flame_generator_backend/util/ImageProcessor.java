package nifreebie.fractal_flame_generator_backend.util;


import nifreebie.fractal_flame_generator_backend.model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
