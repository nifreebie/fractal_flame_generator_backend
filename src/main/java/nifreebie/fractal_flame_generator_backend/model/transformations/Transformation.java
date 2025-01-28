package nifreebie.fractal_flame_generator_backend.model.transformations;



import nifreebie.fractal_flame_generator_backend.model.Point;

import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    String getName();
}
