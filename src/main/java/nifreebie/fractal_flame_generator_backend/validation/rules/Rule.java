package nifreebie.fractal_flame_generator_backend.validation.rules;

public interface Rule {
    boolean check(String value);
    String errorMessage();
}
