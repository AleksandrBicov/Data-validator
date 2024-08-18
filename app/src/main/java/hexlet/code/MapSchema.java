package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer expectedSize;
    private Map<String, BaseSchema<String>> shape = null;


    @Override
    public boolean isValid(Map map) {
        if (map == null) {
            return !required;
        }
        if (expectedSize != null) {
            return map.size() == expectedSize;
        }
        if (shape != null) {
            for (Map.Entry<String, BaseSchema<String>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                if (map.containsKey(key)) {
                    if (!schema.isValid((String) map.get(key))) {
                        return false;
                    }
                } else {
                    if (schema.required) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void sizeof(int i) {
        this.expectedSize = i;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> map) {
        this.shape = new HashMap<>(map);
        return this;
    }
}
