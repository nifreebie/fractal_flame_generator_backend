package nifreebie.fractal_flame_generator_backend.util;

import lombok.experimental.UtilityClass;
import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;

@UtilityClass
public class ImageUtil {
    public static SerializableBufferedImage convertFractalImageToBufferedImage(FractalImage image) {
        SerializableBufferedImage renderedImage = new SerializableBufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }
        return renderedImage;
    }
}
