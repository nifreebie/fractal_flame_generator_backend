package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(point.x() / Math.pow(radius, 2), point.y() / Math.pow(radius, 2));
    }

    @Override
    public String getName() {
        return "Сферическая трансформация";
    }
}
