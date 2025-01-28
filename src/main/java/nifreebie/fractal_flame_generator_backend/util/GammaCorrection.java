package nifreebie.fractal_flame_generator_backend.util;


import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Pixel;

public class GammaCorrection implements ImageProcessor {
    private static final double GAMMA = 1.5;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = GAMMA;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getPixel(x, y).getHitCount() != 0) {
                    image.getPixel(x, y).setNormal(Math.log10(image.getPixel(x, y).getHitCount()));
                    if (image.getPixel(x, y).getNormal() > max) {
                        max = image.getPixel(x, y).getNormal();
                    }
                }
            }
        }
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                pixel.setNormal(image.getPixel(x, y).getNormal() / max);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
            }
        }

    }
}
