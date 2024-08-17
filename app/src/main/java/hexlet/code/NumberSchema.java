package hexlet.code;


public class NumberSchema extends BaseSchema<Integer>{
    private boolean positive = false;
    private boolean range = false;
    private int min;
    private int max;

    @Override
    public boolean isValid(Integer integer) {
        if (integer == null) {
            return !required;
        }
        if (positive && integer <= 0) {
            return false;
        }
        if (range && (integer < min || integer > max)) {
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
        this.min = min;
        this.max = max;
    }
}
