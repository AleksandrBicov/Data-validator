package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

    public final NumberSchema positive() {
        addCheck(
                "positive",
                value -> value == null || value > 0
        );
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> value >= min && value <= max
        );
        return this;
    }
}
