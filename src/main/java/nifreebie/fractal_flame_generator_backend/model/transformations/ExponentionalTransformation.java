package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class ExponentionalTransformation implements Transformation {
    @Override
    public Point apply(Point p) {
        double exp = Math.exp(p.x() - 1.0);
        double x = exp * Math.cos(Math.PI * p.y());
        double y = exp * Math.sin(Math.PI * p.y());
        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Экспонетциальная трансформация";
    }
}
