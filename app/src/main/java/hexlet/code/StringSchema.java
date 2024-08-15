package hexlet.code;

public class StringSchema {
    private boolean required = false;
    private String requiredSubstring = null;
    private Integer requiredInt;

    public StringSchema required() {
        required = true;
        return this;
    }

    public Boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return !required;
        }
        if (requiredSubstring != null && !s.contains(requiredSubstring)) {
            return false;
        }
        if (requiredInt != null && requiredInt > s.length()) {
            return false;
        }
        return true;
    }

    public StringSchema minLength(int i) {
        this.requiredInt = i;
        return this;
    }

    public StringSchema contains(String substring) {
        this.requiredSubstring = substring;
        return this;
    }


}
