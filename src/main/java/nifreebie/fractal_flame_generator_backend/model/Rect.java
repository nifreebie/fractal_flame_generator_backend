package nifreebie.fractal_flame_generator_backend.model;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() >= x && p.x() <= x + width && p.y() >= y && p.y() <= y + height;
    }
}
