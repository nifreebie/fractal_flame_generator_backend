package nifreebie.fractal_flame_generator_backend.util;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SerializableBufferedImage extends BufferedImage implements Serializable {
    public SerializableBufferedImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }
}
