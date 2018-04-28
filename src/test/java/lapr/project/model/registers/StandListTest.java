package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Stand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class StandListTest {
    /**
     * Test of size method, of class StandList.
     */
    @Test
    public void testGetSize() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        List<Stand> arrayTest = new ArrayList<>();
        arrayTest.add(stand);

        listTest.addStand(stand);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);

    }

    /**
     * Test of addStand method, of class StandList.
     */
    @Test
    public void testAddStand() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        List<Stand> arrayTest = new ArrayList<>();
        arrayTest.add(stand);

        assertTrue(listTest.addStand(stand));
        assertFalse(listTest.addStand(stand));

        assertArrayEquals(arrayTest.toArray(), listTest.getStandsList().toArray());
    }

    /**
     * Test of removeStand method, of class StandList.
     */
    @Test
    public void testRemoveStand() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        Stand stand1 = listTest.newStand(40, "Big stand for slow sells.");

        listTest.addStand(stand);
        listTest.addStand(stand1);
        assertTrue(listTest.removeStand(stand));
        assertFalse(listTest.removeStand(stand));
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getStandsList().contains(stand1));

    }

    /**
     * Test of getStandsList method, of class StandList.
     */
    @Test
    public void testGetStandsList() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        Stand stand1 = listTest.newStand(40, "Big stand for slow sells.");


        listTest.addStand(stand);
        listTest.addStand(stand1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getStandsList().contains(stand));
        assertTrue(listTest.getStandsList().contains(stand1));

    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {
        StandList expected = new StandList();
        StandList result = new StandList();
        Stand stand0 = expected.newStand(23, "Small stand for quick sell.");
        Stand stand1 = result.newStand(40, "Big stand for slow sells.");

        expected.addStand(stand0);
        result.addStand(stand1);
        assertNotEquals(expected, result);
    }

    @Test
    public void ensuresGetStand() {
        StandList stands = new StandList();
        Stand stand0 = stands.newStand(23, "Small stand for quick sell.");
        Stand stand1 = stands.newStand(40, "Big stand for slow sells.");
        stands.addStand(stand1);
        stands.addStand(stand0);

        Stand result = stands.getStand(1);
        assertEquals(result, stand0);
        result = stands.getStand(-1);
        assertNull(result);
    }

    /**
     * Test of equals method, of class StandList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        StandList expected = new StandList();
        StandList result = new StandList();
        Stand stand0 = expected.newStand(23, "Small stand for quick sell.");
        Stand stand1 = result.newStand(40, "Big stand for slow sells.");
        expected.addStand(stand0);
        expected.addStand(stand1);
        result.addStand(stand0);
        result.addStand(stand1);

        assertEquals(expected, result);
        assertArrayEquals(expected.getStandsList().toArray(), result.getStandsList().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        StandList expected = new StandList();

        Stand stand0 = expected.newStand(23, "Small stand for quick sell.");
        Stand stand1 = expected.newStand(40, "Big stand for slow sells.");
        expected.addStand(stand0);
        expected.addStand(stand1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        StandList test1 = new StandList();
        StandList test2 = new StandList();
        Stand stand0 = test1.newStand(23, "Small stand for quick sell.");
        Stand stand1 = test2.newStand(40, "Big stand for slow sells.");
        test1.addStand(stand0);
        test1.addStand(stand1);
        test2.addStand(stand1);
        test2.addStand(stand0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getStandsList().toArray(), test1.getStandsList().toArray());
    }

    @Test
    public void ensureSameListIsEqual() {
        StandList expected = new StandList();

        Stand stand0 = expected.newStand(23, "Small stand for quick sell.");
        Stand stand1 = expected.newStand(40, "Big stand for slow sells.");
        expected.addStand(stand0);
        expected.addStand(stand1);

        assertEquals(expected, expected);
    }

    @Test
    public void ensureObjectIsNotEqualToList() {
        StandList expected = new StandList();

        assertNotEquals(expected, new Object());
    }

    /**
     * Test of hashCode method, of class StandList.
     */
    @Test
    public void testHashCode() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        Stand stand1 = listTest.newStand(40, "Big stand for slow sells.");


        listTest.addStand(stand);
        listTest.addStand(stand1);

        int expected = 1584390994;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }

    /**
     * Ensure empty list gets "No stands to show" message
     */
    @Test
    public void ensureEmptyListGetsNoStandsToShowMessage() {
        StandList standList = new StandList();

        List<String> expected = new ArrayList<>();
        expected.add("No stands to show");

        List<String> result = standList.getStandsNameList();

        assertEquals(expected, result);
    }


    /**
     * Test of getStandsNameList method, of class StandList.
     */
    @Test
    public void testGetStandsNameList() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        Stand stand1 = listTest.newStand(40, "Big stand for slow sells.");


        listTest.addStand(stand);
        listTest.addStand(stand1);

        List<String> expected = new ArrayList<>();
        expected.add(Integer.toString(stand.getStandID()));
        expected.add(Integer.toString(stand1.getStandID()));

        List<String> result = listTest.getStandsNameList();
        assertEquals(expected, result);


    }

    /**
     * Test of getStandAreas method, of class StandList.
     */
    @Test
    public void testGetStandAreas() {
        StandList listTest = new StandList();
        Stand stand = listTest.newStand(23, "Small stand for quick sell.");
        Stand stand1 = listTest.newStand(40, "Big stand for slow sells.");


        listTest.addStand(stand);
        listTest.addStand(stand1);

        List<Integer> expected = new ArrayList<>();
        expected.add(23);
        expected.add(40);

        List<Integer> result = listTest.getStandAreas();
        assertEquals(expected, result);
    }
}
