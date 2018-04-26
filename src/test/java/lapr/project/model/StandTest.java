package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Mário Vaz
 */
public class StandTest {

    /**
     * Test of getArea method, of class Stand.
     */
    @Test
    public void ensureGetAreaIsCorrect() {
        Stand stand = new Stand(23, "Small stand for quick sell.", 0);
        int expected = 23;
        int result = stand.getArea();
        assertEquals(expected, result);
    }

    /**
     * Test of getDescription method, of class Stand.
     */
    @Test
    public void ensureGetDescriptionIsCorrect() {
        Stand stand = new Stand(23, "Small stand for quick sell.", 0);
        String expected = "Small stand for quick sell.";
        String result = stand.getDescription();
        assertEquals(expected, result);
    }

    /**
     * Test if stands with same content are equal.
     */
    @Test
    public void ensureSameContentObjectsAreEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        Stand result = new Stand(23, "Small stand for quick sell.", 0);
        assertEquals(expected, result);
    }

    /**
     * Test if a stand is equal to itself.
     */
    @Test
    public void ensureSameObjectIsEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        assertEquals(expected, expected);
    }

    /**
     * Test if stands with the same area but different description are different.
     */
    @Test
    public void ensureSameStandWithSameAreaAndDifferentDescriptionAreNotEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        Stand result = new Stand(23, "Big stand for slow sells.", 0);
        assertNotEquals(expected, result);
    }

    /**
     * Test if stands with the same description but different area are different.
     */
    @Test
    public void ensureSameStandWithSameDescriptionAndDifferentAreaAreNotEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        Stand result = new Stand(40, "Small stand for quick sell.", 0);
        assertNotEquals(expected, result);
    }

    /**
     * Test if stands with different description and area are different.
     */
    @Test
    public void ensureStandWithDifferentAreaAndDescriptionAreNotEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        Stand result = new Stand(40, "Small stand for quick sell.", 0);
        assertNotEquals(expected, result);
    }

    /**
     * Test if a stand and an Object are different.
     */
    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    /**
     * Test if a stand and a null Object are different.
     */
    @Test
    public void ensureNullObjectAreNotEqual() {
        Stand expected = new Stand(23, "Small stand for quick sell.", 0);
        assertNotEquals(expected, null);
    }

    /**
     * Test of hashCode method, of class Stand.
     */
    @Test
    public void ensureHashCodeIsCorrect() {
        Stand stand = new Stand(23, "Small stand for quick sell.", 0);
        int expected = 930224728;
        int result = stand.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void ensureIDreturnedIsCorrect() {
        Stand d = new Stand(2, "idk", 0);
        int actual = d.getStandID();
        int expected = 0;

        assertEquals(actual, expected);
    }

    @Test
    public void ensureSetDescription() {
        Stand s = new Stand(2, "", 0);
        s.setDescription("IDK");

        String actual = s.getDescription();
        String expected = "IDK";

        assertEquals(actual, expected);
    }


}
