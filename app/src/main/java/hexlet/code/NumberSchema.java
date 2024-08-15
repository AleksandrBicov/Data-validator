package hexlet.code;

public class NumberSchema {
    private boolean required = false;
    private boolean positive = false;
    private boolean range = false;
    private int min;
    private int max;

    public Boolean isValid(Integer i) {
        if (i == null) {
            return !required;
        }
        if (positive && i <= 0) {
            return false;
        }
        if (range && (i < min || i > max)) {
            return false;
        }
        return true;
    }


    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public void range(int i, int i1) {
        range = true;
        min = i;
        max = i1;
    }
}
