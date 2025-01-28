package nifreebie.fractal_flame_generator_backend.model.transformations;

import nifreebie.fractal_flame_generator_backend.model.*;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }

    @Override
    public String getName() {
        return "Линейная трансформация";
    }
}
