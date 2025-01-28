package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double atan = Math.atan(point.x() / point.y());
        double sqrt = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double newX = Math.sin(atan) / sqrt;
        double newY = sqrt * Math.cos(atan);
        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Полярная трансформация";
    }
}
