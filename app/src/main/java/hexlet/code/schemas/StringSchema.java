package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public final StringSchema required() {
        addCheck(
                "required",
                value -> value != null && !value.isEmpty()
        );
        return this;
    }

    public final StringSchema minLength(int integer) {
        addCheck(
                "minLength",
                value -> value.length() >= integer
        );
        return this;
    }

    public final StringSchema contains(String substring) {
        addCheck(
                "contains",
                value -> value.contains(substring)
        );
        return this;
    }

}
