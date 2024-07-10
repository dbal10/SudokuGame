package pl.comp.firstjava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class FieldCoordinatesTest {

    private FieldCoordinates fieldCoordinates;
    private FieldCoordinates fieldCoordinatesOther;

    @BeforeEach
    public void setUp() {fieldCoordinates = new FieldCoordinates(2,8); }


    @Test
    public void getAxisXTest() {assertEquals(fieldCoordinates.getAxisX(), 2); }

    @Test
    public void getAxisYTest() {assertEquals(fieldCoordinates.getAxisY(), 8); }

    @Test
    public void toStringTest() {assertNotNull(fieldCoordinates.toString()); }

    @Test
    public void equalsTest() {
        fieldCoordinatesOther = new FieldCoordinates(2,8);
        Integer integer = new Integer(5);

        assertTrue(fieldCoordinates.equals(fieldCoordinates));
        assertFalse(fieldCoordinates.equals(null));
        assertFalse(fieldCoordinates.equals(integer));
        assertTrue(fieldCoordinates.equals(fieldCoordinatesOther) && fieldCoordinatesOther.equals(fieldCoordinates));
    }

    @Test
    public void hashCodeTest() {
        fieldCoordinatesOther = new FieldCoordinates(2,8);
        assertEquals(fieldCoordinates.hashCode(), fieldCoordinatesOther.hashCode());
    }

}
