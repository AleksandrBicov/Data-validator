package hexlet.code;


public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private boolean range = false;
    private int minValue;
    private int maxValue;

    @Override
    public boolean isValid(Integer i) {
        if (i == null) {
            return !required;
        }
        if (positive && i <= 0) {
            return false;
        }
        if (range && (i < minValue || i > maxValue)) {
            return false;
        }
        return true;
    }


    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public void range(int min, int max) {
        this.range = true;
        this.minValue = min;
        this.maxValue = max;
    }
}
