package lapr.project.model.registers;

import lapr.project.model.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class KeywordListTest {

    /**
     * Test of getSize method, of class KeywordList.
     */
    @Test
    public void testGetSize() {
        Keyword keyword = new Keyword("k1");
        List<Keyword> arrayTest = new ArrayList<>();
        arrayTest.add(keyword);
        KeywordList listTest = new KeywordList();
        listTest.addKeyword(keyword);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);
    }

    /**
     * Test of addKeyword method, of class KeywordList.
     */
    @Test
    public void testAddKeyword() {
        Keyword keyword = new Keyword("k1");
        List<Keyword> arrayTest = new ArrayList<>();
        arrayTest.add(keyword);
        KeywordList listTest = new KeywordList();
        assertTrue(listTest.addKeyword(keyword));
        assertArrayEquals(arrayTest.toArray(), listTest.getKeywordsList().toArray());
    }

    /**
     * Test of removeKeyword method, of class KeywordList.
     */
    @Test
    public void testRemoveKeyword() {
        Keyword keyword = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");

        KeywordList listTest = new KeywordList();
        listTest.addKeyword(keyword);
        listTest.addKeyword(keyword1);
        assertTrue(listTest.removeKeyword(keyword));
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getKeywordsList().contains(keyword1));
    }

    /**
     * Test of getKeywordsList method, of class KeywordList.
     */
    @Test
    public void testGetKeywordsList() {
        Keyword keyword = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");

        KeywordList listTest = new KeywordList();
        listTest.addKeyword(keyword);
        listTest.addKeyword(keyword1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getKeywordsList().contains(keyword));
        assertTrue(listTest.getKeywordsList().contains(keyword1));
    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {
        Keyword keyword0 = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");
        KeywordList expected = new KeywordList();
        KeywordList result = new KeywordList();
        expected.addKeyword(keyword0);
        result.addKeyword(keyword1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class KeywordList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        Keyword keyword0 = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");
        KeywordList expected = new KeywordList();
        KeywordList result = new KeywordList();
        expected.addKeyword(keyword0);
        expected.addKeyword(keyword1);
        result.addKeyword(keyword0);
        result.addKeyword(keyword1);

        assertArrayEquals(expected.getKeywordsList().toArray(), result.getKeywordsList().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        Keyword keyword0 = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");
        KeywordList expected = new KeywordList();
        expected.addKeyword(keyword0);
        expected.addKeyword(keyword1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        Keyword keyword0 = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");
        KeywordList test1 = new KeywordList();
        KeywordList test2 = new KeywordList();
        test1.addKeyword(keyword0);
        test1.addKeyword(keyword1);
        test2.addKeyword(keyword1);
        test2.addKeyword(keyword0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getKeywordsList().toArray(), test1.getKeywordsList().toArray());
    }

    /**
     * Test of hashCode method, of class KeywordList.
     */
    @Test
    public void testHashCode() {
        Keyword keyword = new Keyword("k1");
        Keyword keyword1 = new Keyword("k2");

        KeywordList listTest = new KeywordList();
        listTest.addKeyword(keyword);
        listTest.addKeyword(keyword1);

        int expected = 114440;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }
}
