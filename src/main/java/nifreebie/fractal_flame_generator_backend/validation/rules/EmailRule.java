package nifreebie.fractal_flame_generator_backend.validation.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRule implements Rule {
    @Override
    public boolean check(String value) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    @Override
    public String errorMessage() {
        return "Invalid email address";
    }
}
