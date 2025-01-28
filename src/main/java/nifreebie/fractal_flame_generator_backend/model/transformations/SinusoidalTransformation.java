package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }

    @Override
    public String getName() {
        return "Синусоидальная трансформация";
    }
}
