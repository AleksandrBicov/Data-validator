package hexlet.code;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        var v = new Validator();

        var schema = v.map();

        schema.isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(new HashMap<>()); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        schema.isValid(data); // true

        schema.sizeof(2);

        schema.isValid(data);  // false
        data.put("key2", "value2");
        schema.isValid(data); // true

    }
}