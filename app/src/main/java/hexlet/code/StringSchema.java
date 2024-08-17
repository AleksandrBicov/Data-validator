package hexlet.code;

public class StringSchema extends BaseSchema<String> {

    private String requiredSubstring = null;
    private Integer requiredInt;

    @Override
    public boolean isValid(String string) {
        if (string == null || string.isEmpty()) {
            return !required;
        }
        if (requiredSubstring != null && !string.contains(requiredSubstring)) {
            return false;
        }
        if (requiredInt != null && requiredInt > string.length()) {
            return false;
        }
        return true;
    }

    public StringSchema minLength(int integer) {
        this.requiredInt = integer;
        return this;
    }

    public StringSchema contains(String substring) {
        this.requiredSubstring = substring;
        return this;
    }

}
