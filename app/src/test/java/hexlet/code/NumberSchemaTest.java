package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void setUp() {
        schema = new NumberSchema();
    }

    @Test
    public void testIsValid_PositiveNumber() {
        assertTrue(schema.isValid(5));
    }

    @Test
    public void testIsValid_NullBeforeRequired() {
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testIsValid_NullAfterPositive() {
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    public void testIsValid_NullAfterRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testIsValid_PositiveNumberAfterRequired() {
        schema.required();
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testIsValid_NegativeNumberAfterPositive() {
        schema.positive();
        assertFalse(schema.isValid(-10));
    }

    @Test
    public void testIsValid_ZeroAfterPositive() {
        schema.positive();
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRange_WithinRange() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testRange_BelowMin() {
        schema.range(5, 10);
        assertFalse(schema.isValid(4));
    }

    @Test
    public void testRange_AboveMax() {
        schema.range(5, 10);
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testRange_WithPositiveConstraint() {
        schema.positive().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
    }

    @Test
    public void testRange_WithRequiredConstraint() {
        schema.required().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(null));
    }
}