package nifreebie.fractal_flame_generator_backend.service.generator;


import lombok.SneakyThrows;
import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Rect;
import nifreebie.fractal_flame_generator_backend.model.transformations.AffineTransformation;
import nifreebie.fractal_flame_generator_backend.model.transformations.Transformation;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadGenerator extends AbstractGenerator {
    public MultiThreadGenerator(
            int affineCount,
            int samples,
            int iterPerSample,
            int symmetry,
            List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    @SneakyThrows
    public void renderAllImage(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < samples; i++) {
            executorService.execute(
                    () -> renderOneSample(image, world, affineTransformations)
            );
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }
}
