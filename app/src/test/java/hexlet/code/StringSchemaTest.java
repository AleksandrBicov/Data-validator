package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StringSchemaTest {

    private Validator validator;

    @BeforeEach
    public final void setUp() {
        validator = new Validator();
    }

    @Test
    public void testStringSchemaCreation() {
        StringSchema stringSchema = validator.string();
        assertNotNull(stringSchema);
    }

    @Test
    public void testRequired() {
        StringSchema stringSchema = validator.string();
        stringSchema.required();
        assertFalse(stringSchema.isValid("")); // false, так как строка обязательна
        assertFalse(stringSchema.isValid(null)); // false, так как строка обязательна
        assertTrue(stringSchema.isValid("hexlet")); // true
    }

    @Test
    public void testIsValid() {
        StringSchema stringSchema = validator.string();
        assertTrue(stringSchema.isValid("")); // true, так как строка не обязательна
        assertTrue(stringSchema.isValid(null)); // true, так как строка не обязательна

        stringSchema.required();
        assertFalse(stringSchema.isValid("")); // false, так как строка обязательна
        assertFalse(stringSchema.isValid(null)); // false, так как строка обязательна
        assertTrue(stringSchema.isValid("hexlet")); // true
    }

    @Test
    public void testMinLength() {
        StringSchema stringSchema = validator.string();
        stringSchema.minLength(5);
        assertTrue(stringSchema.isValid("hexlet")); // true, так как длина 6
        assertFalse(stringSchema.isValid("hex")); // false, так как длина 3
    }

    @Test
    public void testContains() {
        StringSchema stringSchema = validator.string();
        stringSchema.contains("hex");
        assertTrue(stringSchema.isValid("hexlet")); // true, так как содержит "hex"
        assertFalse(stringSchema.isValid("let")); // false, так как не содержит "hex"
    }

    @Test
    public void testCombined() {
        StringSchema stringSchema = validator.string();
        stringSchema.required();
        stringSchema.minLength(5);
        stringSchema.contains("hex");
        assertTrue(stringSchema.isValid("hexlet")); // true, так как все условия выполнены
        assertFalse(stringSchema.isValid("hex")); // false, так как длина 3
        assertFalse(stringSchema.isValid("let")); // false, так как не содержит "hex"
        assertFalse(stringSchema.isValid("")); // false, так как строка обязательна
        assertFalse(stringSchema.isValid(null)); // false, так как строка обязательна
    }
}
