package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

    public MapSchema sizeOf(int integer) {
        addCheck(
                "sizeof",

                value -> value.size() == integer
        );
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> map) {
        addCheck(
                "shape",
                value -> map.entrySet().stream().allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<String> schema = entry.getValue();
                    if (value.containsKey(key)) {
                        return schema.isValid((String) value.get(key));
                    } else {
                        return true;
                    }
                })
        );
        return this;
    }

}
