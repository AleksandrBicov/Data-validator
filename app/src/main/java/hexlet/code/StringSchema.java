package hexlet.code;

public class StringSchema {
    private boolean isRequired = false;
    private String requiredSubstring = null;
    private Integer requiredInt;

    public void required() {
        isRequired = true;
    }

    public Boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return !isRequired;
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
