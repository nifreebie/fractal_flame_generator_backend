package nifreebie.fractal_flame_generator_backend.util;


import lombok.experimental.UtilityClass;
import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Pixel;
import nifreebie.fractal_flame_generator_backend.model.Point;
import nifreebie.fractal_flame_generator_backend.model.Rect;

@UtilityClass
public class FractalUtil {
    public static Pixel resolvePixel(Rect rect, Point point, FractalImage image) {
        if (!rect.contains(point)) {
            return null;
        }
        return image.getPixel(
            (int) ((point.x() - rect.x()) / rect.width() * image.getWidth()),
            (int) ((point.y() - rect.y()) / rect.height() * image.getHeight())
        );
    }
}
