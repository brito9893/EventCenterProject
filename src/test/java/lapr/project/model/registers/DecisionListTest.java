package lapr.project.model.registers;

import lapr.project.model.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class DecisionListTest {

    /**
     * Test of getSize method, of class DecisionList.
     */
    @Test
    public void testGetSize() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        List<Decision> arrayTest = new ArrayList<>();
        arrayTest.add(decision);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        listTest.addDecision(decision);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);

    }

    @Test
    public void testAddDecision() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }

        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();

        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        assertTrue(listTest.addDecision(decision));
        assertTrue(listTest.addDecision(decision1));
        assertFalse(listTest.addDecision(decision1));

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getDecisionsList().contains(decision));
        assertTrue(listTest.getDecisionsList().contains(decision1));
    }

    /**
     * Test of addDecision method, of class DecisionList.
     */
    @Test
    public void newDecisionTeste() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);

        Decision decision2 = listTest.newDecision(rates, true, "Long justification about this decision.", fae);

        assertEquals(decision, decision2);

    }

    /**
     * Test of removeDecision method, of class DecisionList.
     */
    @Test
    public void testRemoveDecision() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        assertTrue(listTest.addDecision(decision));
        assertTrue(listTest.addDecision(decision1));
        assertTrue(listTest.removeDecision(decision));
        assertFalse(listTest.removeDecision(decision));
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getDecisionsList().contains(decision1));
    }

    /**
     * Test of getDecisionsList method, of class DecisionList.
     */
    @Test
    public void testGetDecisionsList() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        listTest.addDecision(decision);
        listTest.addDecision(decision1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getDecisionsList().contains(decision));
        assertTrue(listTest.getDecisionsList().contains(decision1));

    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision0 = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha123", "luiscunha231233", "1151342581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision123.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        DecisionList expected = new DecisionList(app);
        DecisionList result = new DecisionList(application1);
        expected.addDecision(decision0);
        result.addDecision(decision1);
        assertNotEquals(expected, result);
    }

    @Test
    public void ensuresSameListIsEqual() {

        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision0 = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Manuel Cunha123", "manuelcunha231233", "manuel1@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision123.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        DecisionList expected = new DecisionList(app);

        expected.addDecision(decision0);
        expected.addDecision(decision1);
        assertEquals(expected, expected);
    }

    /**
     * Test of equals method, of class DecisionList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision0 = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);


        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        DecisionList expected = new DecisionList(app);
        DecisionList result = new DecisionList(app);
        expected.addDecision(decision0);
        expected.addDecision(decision1);
        result.addDecision(decision0);
        result.addDecision(decision1);

        assertArrayEquals(expected.getDecisionsList().toArray(), result.getDecisionsList().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision0 = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        DecisionList expected = new DecisionList(app);
        DecisionList result = null;
        expected.addDecision(decision0);
        expected.addDecision(decision1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision0 = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        Application app2 = new Application(representative, "spacex", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        DecisionList test1 = new DecisionList(app);
        DecisionList test2 = new DecisionList(app2);
        test1.addDecision(decision0);
        test1.addDecision(decision1);
        test2.addDecision(decision1);
        test2.addDecision(decision0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertTrue(test1.getDecisionsList().containsAll(test2.getDecisionsList()));
    }

    /**
     * Test of hashCode method, of class DecisionList.
     */
    @Test
    public void testHashCode() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates2, false, "Long justification about this decision.", fae2);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        listTest.addDecision(decision);
        listTest.addDecision(decision1);

        int expected = -1494177799;
        assertEquals(expected, decision.hashCode());
        assertEquals(expected, decision.hashCode());
    }

    @Test
    public void testNullObjectInHashCode() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        listTest.addDecision(decision);
        listTest.addDecision(null);

        int expected = -1494177799;
        assertEquals(expected, decision.hashCode());

    }

    /**
     * Test of getApplication method, of class DecisionList.
     */
    @Test
    public void testGetApplication() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        List<Decision> arrayTest = new ArrayList<>();
        arrayTest.add(decision);

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        DecisionList listTest = new DecisionList(application);
        listTest.addDecision(decision);

        Application expected = application;
        Application result = listTest.getApplication();

        assertEquals(expected, result);

    }


}
