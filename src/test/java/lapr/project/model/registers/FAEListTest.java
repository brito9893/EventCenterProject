package lapr.project.model.registers;

import lapr.project.model.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class FAEListTest {
    /**
     * Test of getSize method, of class FAEList.
     */
    @Test
    public void testGetSize() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));

        List<FAE> arrayTest = new ArrayList<>();
        arrayTest.add(fae);
        FAEList listTest = new FAEList();
        listTest.addFAE(fae);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);

    }

    /**
     * Test of addFAE method, of class FAEList.
     */
    @Test
    public void testAddFAE() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        List<FAE> arrayTest = new ArrayList<>();
        arrayTest.add(fae);
        FAEList listTest = new FAEList();
        assertTrue(listTest.addFAE(fae));

        assertArrayEquals(arrayTest.toArray(), listTest.getFAEsList().toArray());
    }

    @Test
    public void testAddSameFAE() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAEList listTest = new FAEList();
        assertTrue(listTest.addFAE(fae));
        assertFalse(listTest.addFAE(fae));
        int expected = 1;
        assertEquals(expected, listTest.getSize());
    }

    /**
     * Test of removeFAE method, of class FAEList.
     */
    @Test
    public void testRemoveFAE() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        FAEList listTest = new FAEList();
        assertTrue(listTest.addFAE(fae));
        assertTrue(listTest.addFAE(fae1));
        assertTrue(listTest.removeFAE(fae));
        assertFalse(listTest.removeFAE(fae));
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getFAEsList().contains(fae1));

    }

    /**
     * Test of getFAEsList method, of class FAEList.
     */
    @Test
    public void testGetFAEsList() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        FAEList listTest = new FAEList();
        listTest.addFAE(fae);
        listTest.addFAE(fae1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getFAEsList().contains(fae));
        assertTrue(listTest.getFAEsList().contains(fae1));

    }

    @Test
    public void ensuresisFaeIsWorking() {

        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList expected = new FAEList();
        expected.addFAE(fae0);

        assertTrue(expected.isFae(fae0));
        assertFalse(expected.isFae(fae1));
    }


    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList expected = new FAEList();
        FAEList result = new FAEList();
        expected.addFAE(fae0);
        result.addFAE(fae1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class FAEList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList expected = new FAEList();
        FAEList result = new FAEList();
        expected.addFAE(fae0);
        expected.addFAE(fae1);
        result.addFAE(fae0);
        result.addFAE(fae1);

        assertArrayEquals(expected.getFAEsList().toArray(), result.getFAEsList().toArray());
    }

    @Test
    public void ensureSameListIsEqual() {
        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList expected = new FAEList();
        expected.addFAE(fae0);
        expected.addFAE(fae1);


        assertEquals(expected, expected);
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList expected = new FAEList();
        expected.addFAE(fae0);
        expected.addFAE(fae1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        FAE fae0 = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        FAEList test1 = new FAEList();
        FAEList test2 = new FAEList();
        test1.addFAE(fae0);
        test1.addFAE(fae1);
        test2.addFAE(fae1);
        test2.addFAE(fae0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getFAEsList().toArray(), test1.getFAEsList().toArray());
    }

    /**
     * Test of hashCode method, of class FAEList.
     */
    @Test
    public void testHashCode() {
        FAE fae = new FAE(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        FAE fae1 = new FAE(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        FAEList listTest = new FAEList();
        listTest.addFAE(fae);
        listTest.addFAE(fae1);

        int expected = -2093635754;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }

    /**
     * Ensure empty list gets "No FAEs to show" message
     */
    @Test
    public void ensureEmptyListGetsNoFAEsToShowMessage() {
        FAEList faeList = new FAEList();

        List<String> expected = new ArrayList<>();
        expected.add("No FAEs to show");

        List<String> result = faeList.getFAEsNameList();

        assertEquals(expected, result);
    }
}
