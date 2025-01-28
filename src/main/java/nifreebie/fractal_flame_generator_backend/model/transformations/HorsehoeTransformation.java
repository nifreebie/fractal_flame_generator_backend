package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class HorsehoeTransformation implements Transformation {
    @Override
    public Point apply(Point p) {
        double x = (p.x() - p.y()) * (p.x() + p.y()) / Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double y = 2 * p.x() * p.y() / Math.sqrt(p.x() * p.x() + p.y() * p.y());
        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Трансформация в виде подковы";
    }
}
