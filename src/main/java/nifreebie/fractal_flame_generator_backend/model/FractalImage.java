package nifreebie.fractal_flame_generator_backend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FractalImage implements Serializable {
    private final Pixel[][] data;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                data[x][y] = new Pixel(0, 0, 0, 0, 0);
            }
        }
    }

    public Pixel getPixel(int x, int y) {
        return data[x][y];
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
