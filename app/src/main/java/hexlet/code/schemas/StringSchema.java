package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int integer) {
        addCheck(
                "minLength",
                value -> value.length() >= integer
        );
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(
                "contains",
                value -> value.contains(substring)
        );
        return this;
    }

}
