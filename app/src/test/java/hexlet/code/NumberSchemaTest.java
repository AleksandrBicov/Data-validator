package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void setUp() {
        schema = new NumberSchema();
    }


    @Test
    public void testIsValidPositiveNumber() {
        assertTrue(schema.isValid(5));
    }

    @Test
    public void testIsValidNullBeforeRequired() {
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testIsValidNullAfterPositive() {
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    public void testIsValidNullAfterRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testIsValidPositiveNumberAfterRequired() {
        schema.required();
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testIsValidNegativeNumberAfterPositive() {
        schema.positive();
        assertFalse(schema.isValid(-10));
    }

    @Test
    public void testIsValidZeroAfterPositive() {
        schema.positive();
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRangeWithinRange() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testRangeBelowMin() {
        schema.range(5, 10);
        assertFalse(schema.isValid(4));
    }

    @Test
    public void testRangeAboveMax() {
        schema.range(5, 10);
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testRangeWithPositiveConstraint() {
        schema.positive().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
    }

    @Test
    public void testRangeWithRequiredConstraint() {
        schema.required();
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(null));
    }
}
