package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point p) {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double sinR = Math.sin(r);
        double cosR = Math.cos(r);
        double x = p.x() * cosR - p.y() * sinR;
        double y = p.x() * sinR + p.y() * cosR;
        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Трансформация в виде спирали";
    }
}
