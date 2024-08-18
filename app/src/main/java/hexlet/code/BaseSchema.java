package hexlet.code;

public abstract class BaseSchema<T> {

    boolean required;
    private Integer requiredInt;

    public BaseSchema<T> required() {
        required = true;
        return this;
    }

    public abstract boolean isValid(T value);
}
