package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> value == null || value > 0
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(
                "range",
                value -> value >= min && value <= max
        );
        return this;
    }
}
