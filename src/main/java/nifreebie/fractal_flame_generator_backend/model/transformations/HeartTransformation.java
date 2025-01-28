package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point.x() == 0 && point.y() == 0) {
            return new Point(0, 0);
        }
        double sqrt = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double a = sqrt * Math.atan(point.y() / point.x());
        double newX = sqrt * Math.sin(a);
        double newY = -sqrt * Math.cos(a);
        return new Point(newX, newY);
    }

    @Override
    public String getName() {
        return "Трансформация в виде сердца";
    }
}
