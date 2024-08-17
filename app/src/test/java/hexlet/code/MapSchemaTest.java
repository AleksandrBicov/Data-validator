package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MapSchemaTest {

    private Validator validator;
    private MapSchema schema;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    public void testInitialIsValid() {
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testIsValidWithData() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeof() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeofWithEmptyMap() {
        schema.required();
        schema.sizeof(1);
        var data = new HashMap<String, String>();
        assertFalse(schema.isValid(data));
    }

    @Test
    public void testSizeofWithNull() {
        schema.required();
        schema.sizeof(1);
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testSizeofWithExactSize() {
        schema.required();
        schema.sizeof(1);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeofWithExcessSize() {
        schema.required();
        schema.sizeof(1);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertFalse(schema.isValid(data));
    }
}