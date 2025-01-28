package nifreebie.fractal_flame_generator_backend.validation;

import nifreebie.fractal_flame_generator_backend.validation.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static void validate(String value, List<Rule> rules) {
        List<String> errors = new ArrayList<>();
        if (value == null || value.isBlank()) {
            errors.add("Field should be not blank");
        } else {
            for (Rule rule : rules) {
                if (!rule.check(value)) {
                    errors.add(rule.errorMessage());
                }
            }
        }
        if (!errors.isEmpty()) throw new ValidationException(errors);
    }
}
