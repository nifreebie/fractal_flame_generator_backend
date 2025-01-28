package nifreebie.fractal_flame_generator_backend.service.generator;


import lombok.AllArgsConstructor;
import nifreebie.fractal_flame_generator_backend.model.Point;
import nifreebie.fractal_flame_generator_backend.model.*;
import nifreebie.fractal_flame_generator_backend.model.transformations.AffineTransformation;
import nifreebie.fractal_flame_generator_backend.model.transformations.Transformation;
import nifreebie.fractal_flame_generator_backend.util.FractalUtil;
import nifreebie.fractal_flame_generator_backend.util.RectUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public abstract class AbstractGenerator implements Generator {
    private static final int STEPS_FOR_NORMALIZATION = 20;

    protected final int affineCount;
    protected final int samples;
    protected final int iterPerSample;
    protected final int symmetry;
    protected final List<Transformation> variations;

    @Override
    public FractalImage render(int width, int height, Rect world) {
        FractalImage image = new FractalImage(width, height);
        List<AffineTransformation> affineTransformations = generateAffineTransformations();
        renderAllImage(image, world, affineTransformations);
        return image;
    }

    public abstract void renderAllImage(
            FractalImage image,
            Rect world,
            List<AffineTransformation> affineTransformations
    );

    private List<AffineTransformation> generateAffineTransformations() {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < affineCount; i++) {
            AffineTransformation transformation =
                    new AffineTransformation(AffineCoefficient.generateRandom(ThreadLocalRandom.current()));
            affineTransformations.add(transformation);
        }
        return affineTransformations;
    }

    protected void renderOneSample(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        Point currentPoint = RectUtil.randomPoint(world);
        for (int step = -STEPS_FOR_NORMALIZATION; step < iterPerSample; ++step) {
            AffineTransformation affine =
                    affineTransformations.get((ThreadLocalRandom.current().nextInt(affineTransformations.size())));
            Transformation variation = variations.get((ThreadLocalRandom.current().nextInt(variations.size())));
            currentPoint = affine.apply(currentPoint);
            currentPoint = variation.apply(currentPoint);
            if (step > 0) {
                double theta = 0.0;
                for (int chunk = 0; chunk < symmetry; theta += Math.PI * 2 / symmetry, ++chunk) {
                    var point = RectUtil.rotatePoint(world, currentPoint, theta);
                    processPoint(world, image, point, affine);
                }
            }
        }
    }

    private void processPoint(Rect world, FractalImage image, Point point, AffineTransformation affine) {
        if (!world.contains(point)) {
            return;
        }
        Pixel pixel = FractalUtil.resolvePixel(world, point, image);
        if (pixel != null) {
            synchronized (pixel) {
                Color color = affine.getAffineCoefficient().color();
                pixel.saturateHitCount(color);
            }
        }
    }
}
