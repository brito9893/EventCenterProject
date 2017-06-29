package lapr.project.model;


import org.junit.*;

import static org.junit.Assert.*;

/**
 * Example of a domain class that is used in Candidatura.
 * Created by Nuno Bettencourt [NMB] on 29/05/16.
 */
public class KeywordTest {


    @Test
    public void ensureSameContentObjectsAreEqual() {
        Keyword expected = new Keyword("Doors");
        Keyword result = new Keyword("Doors");
        assertEquals(expected, result);
    }

    @Test
    public void ensureDifferentClassIsDifferent() {
        Keyword expected = new Keyword("Doors");
        User result = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertNotEquals(expected, result);
    }


    @Test
    public void ensureSameObjectIsEqual() {
        Keyword expected = new Keyword("Doors");
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Keyword expected = new Keyword("Doors");
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    /**
     * Test if a keyword and a null Object are different.
     */
    @Test
    public void ensureNullObjectAreNotEqual() {
        Keyword expected = new Keyword("Doors");
        Object result = null;
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        Keyword firstKeyword = new Keyword("Doors");

        int expected = 66216549;
        int result = firstKeyword.hashCode();
        assertEquals(expected, result);
    }

}