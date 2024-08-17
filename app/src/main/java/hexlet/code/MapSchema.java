package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize;

    @Override
    public boolean isValid(Map map) {
        if (map == null) {
            return !required;
        }
        if (expectedSize != null) {
            return map.size() == expectedSize;
        }
        return true;
    }


    public void sizeof(int i) {
        this.expectedSize = i;
    }
}
