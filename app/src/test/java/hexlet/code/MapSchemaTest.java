package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    public void testShapeValidation1() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required());


        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true
    }

    @Test
    public void testShapeValidation2() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required());

        schema.shape(schemas);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false
    }

    @Test
    public void testShapeValidation3() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        StringSchema lastNameSchema = (StringSchema) validator.string().required();
        lastNameSchema.minLength(2);
        schemas.put("lastName", lastNameSchema);

        schema.shape(schemas);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
