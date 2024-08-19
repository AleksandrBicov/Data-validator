package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(T value) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            return !required;
        }
        return checks.values().stream().allMatch(predicate -> predicate.test(value));
    }

    public BaseSchema<T> required() {
        required = true;
        return this;
    }
}
