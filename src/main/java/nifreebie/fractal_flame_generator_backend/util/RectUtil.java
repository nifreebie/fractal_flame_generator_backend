package nifreebie.fractal_flame_generator_backend.util;


import lombok.experimental.UtilityClass;
import nifreebie.fractal_flame_generator_backend.model.Point;
import nifreebie.fractal_flame_generator_backend.model.Rect;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RectUtil {
    public static Point randomPoint(Rect rect) {
        return new Point(
                rect.x() + ThreadLocalRandom.current().nextDouble() * rect.width(),
                rect.y() + Math.random() * rect.height()
        );
    }

    public static Point rotatePoint(Rect rect, Point point, double angle) {
        double centerX = rect.x() + rect.width() / 2;
        double centerY = rect.y() + rect.height() / 2;
        double x = (point.x() - centerX) * Math.cos(angle)
                - (point.y() - centerY) * Math.sin(angle) + centerX;
        double y = (point.x() - centerX) * Math.sin(angle)
                + (point.y() - centerY) * Math.cos(angle) + centerY;
        return new Point(x, y);
    }
}
