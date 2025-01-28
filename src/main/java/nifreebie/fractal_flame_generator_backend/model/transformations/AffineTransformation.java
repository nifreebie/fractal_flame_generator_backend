package nifreebie.fractal_flame_generator_backend.model.transformations;


import lombok.AllArgsConstructor;
import lombok.Getter;
import nifreebie.fractal_flame_generator_backend.model.*;

@AllArgsConstructor
@Getter
public class AffineTransformation implements Transformation {
    private AffineCoefficient affineCoefficient;

    @Override
    public Point apply(Point point) {
        double x = affineCoefficient.a() + point.x() * affineCoefficient.b() + point.y() * affineCoefficient.c();
        double y = affineCoefficient.d() + point.x() * affineCoefficient.e() + point.y() * affineCoefficient.f();
        return new Point(x, y);
    }

    @Override
    public String getName() {
        return "Афинное преобразование";
    }
}
