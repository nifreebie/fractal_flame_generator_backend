package nifreebie.fractal_flame_generator_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class Pixel implements Serializable {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public void saturateHitCount(Color color) {
        if (hitCount == 0) {
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        } else {
            r = (r + color.getRed()) / 2;
            g = (g + color.getGreen()) / 2;
            b = (b + color.getBlue()) / 2;
        }
        hitCount++;
    }
}
