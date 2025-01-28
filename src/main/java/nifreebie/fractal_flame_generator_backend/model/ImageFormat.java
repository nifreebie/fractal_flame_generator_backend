package nifreebie.fractal_flame_generator_backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageFormat {
    PNG("png"),
    BMP("bmp"),
    JPEG("jpg");

    private final String formatName;
}
