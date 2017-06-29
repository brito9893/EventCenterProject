package lapr.project.model;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class DecisionTest {

    /**
     * Test of getActualDecision method, of class Decision.
     */
    @Test
    public void ensureGetDecisionIsCorrect() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        boolean result = decision.getActualDecision();
        assertEquals(true, result);
    }

    @Test
    public void ensureGetAuthorOsTheSameDecisionAreEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        assertEquals(fae, decision.getDecisionAuthor());
    }

    @Test
    public void ensureGetAuthorOsTheDifferentDecisionAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 4; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 4; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("joao Cunha", "j243", "j243@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision2 = new Decision(rates2, true, "Long justification about this decision.", fae2);

        assertNotEquals(decision2.getDecisionAuthor(), decision.getDecisionAuthor());
    }

    /**
     * Test of getJustification method, of class Decision.
     */
    @Test
    public void ensureGetJustificationIsCorrect() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Long justification about this decision.", fae);

        String expected = "Long justification about this decision.";
        String result = decision.getJustification();
        assertEquals(expected, result);
    }

    /**
     * Test if decisions with same content are equal.
     */
    @Test
    public void ensureSameContentObjectsAreEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Long justification about this decision.", fae);
        Decision result = new Decision(rates, true, "Long justification about this decision.", fae);
        assertEquals(expected, result);
    }

    /**
     * Test if a decision is equal to itself.
     */
    @Test
    public void ensureSameObjectIsEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Long justification about this decision.", fae);
        assertEquals(expected, expected);
    }

    /**
     * Test if decision with the same logic value but different justification
     * are different.
     */
    @Test
    public void ensureSameDecisionWithSameLogicValueAndDifferentJustificationAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision result = new Decision(rates2, true, "Long justification about this decision.", fae2);
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameDecisionWithDifferentRatesAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision result = new Decision(rates2, true, "Long justification about this decision.", fae2);
        assertNotEquals(expected, result);
    }

    /**
     * Test if decision with the same justification but different logic value
     * are different.
     */
    @Test
    public void ensureSameDecisionWithSameJustificationAndDifferentLogicValueAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Long justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision result = new Decision(rates2, false, "Long justification about this decision.", fae2);
        assertNotEquals(expected, result);
    }

    /**
     * Test if decision with different justification and logic value are
     * different.
     */
    @Test
    public void ensureDecisionWithDifferentLogicValueAndJustificationAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        List<Integer> rates2 = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae2 = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision result = new Decision(rates2, false, "Long justification about this decision.", fae2);
        assertNotEquals(expected, result);
    }

    /**
     * Test if a decision and an Object are different.
     */
    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureDifferentFaeAreNotEqual() {

        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        FAE fae2 = new FAE(new User("joao Cunha", "j243", "j243@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision result = new Decision(rates, true, "Short justification about this decision.", fae2);
        assertNotEquals(expected, result);

    }

    @Test
    public void ensureDifferentRatesAreNotEqual() {

        List<Integer> ratesa = new ArrayList<>();
        ratesa.add(0);
        ratesa.add(0);
        ratesa.add(0);
        ratesa.add(0);


        List<Integer> ratesb = new ArrayList<>();
        ratesb.add(1);
        ratesb.add(1);
        ratesb.add(1);
        ratesb.add(1);


        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(ratesa, true, "Short justification about this decision.", fae);
        Decision result = new Decision(ratesb, true, "Short justification about this decision.", fae);
        assertNotEquals(expected, result);

    }

    /**
     * Test if a decision and a null Object are different.
     */
    @Test
    public void ensureNullObjectAreNotEqual() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision expected = new Decision(rates, true, "Short justification about this decision.", fae);
        assertNotEquals(expected, null);
    }

    /**
     * Test of hashCode method, of class Decision.
     */
    @Test
    public void ensureHashCodeIsCorrect() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Short justification about this decision.", fae);
        int expected = 747177657;

        assertEquals(expected, decision.hashCode());
        assertEquals(expected, decision.hashCode());
    }

    /**
     * Test of getRates method, of class Decision.
     */
    @Test
    public void testGetRates() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Short justification about this decision.", fae);

        int expected = 5;
        int result = rates.size();

        assertEquals(expected, result);

    }

    /**
     * Test of calcAverageEvaluation method, of class Decision.
     */
    @Test
    public void testCalcAverageEvaluation() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }

        FAE fae = new FAE(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, "Short justification about this decision.", fae);

        double expected = 2.5;
        double result = decision.calcAverageEvaluation();

        assertEquals(expected, result, 2.5);
    }


}
