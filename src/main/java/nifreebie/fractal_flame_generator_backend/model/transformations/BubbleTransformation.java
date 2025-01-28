package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class BubbleTransformation implements Transformation {
    @Override
    @SuppressWarnings("MagicNumber")
    public Point apply(Point p) {
        double r2 = p.x() * p.x() + p.y() * p.y();
        double x = 4 * p.x() / (r2 + 4);
        double y = 4 * p.y() / (r2 + 4);
        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Пузырьковая трансформация";
    }
}
