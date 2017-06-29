package lapr.project.model;


import lapr.project.model.registers.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Class to demonstrate a Application simple example.
 *
 * @author Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
public class ApplicationTest {

    @Test
    public void ensureAddKeywordIsWorking() {
        List<Keyword> expectedKeywordList = new ArrayList<>();
        expectedKeywordList.add(new Keyword("Doors"));

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        KeywordList resultList = application.getKeywordList();

        assertArrayEquals(expectedKeywordList.toArray(), resultList.getKeywordsList().toArray());

    }

    @Test
    public void ensureGetDescription() {


        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        String result = application.getDescription();
        String expected = "ELON MUSK FOR THE WIN";
        assertEquals(expected, result);

    }

    /**
     * Test of getDecision method, of class Application.
     */
    @Test
    public void ensureGetDecision() {
        List<Integer> rates = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            rates.add(a);
        }
        FAE fae = new FAE(new User("Liliana Lopes", "lilianalopes98", "1161560@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision = new Decision(rates, true, " ", fae);

        FAE fae1 = new FAE(new User("Ricardo Lopes", "ricardolopes07", "1161561@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Decision decision1 = new Decision(rates, true, " ", fae);


        Representative representative = new Representative(new User("Liliana Lopes", "lilianalopes98", "1161560@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, " ", " ", 0, 0, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        application.getDecisionList().addDecision(decision);
        application.getDecisionList().addDecision(decision1);


        assertTrue(application.getDecision());

    }

    @Test
    public void ensureSetDescription() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));


        String expected = "MODEL 3";
        application.setDescription(expected);
        String result = application.getDescription();

        assertEquals(expected, result);

    }

    @Test
    public void ensureSetKeywordList() {

        KeywordList expectedKeywordList = new KeywordList();
        expectedKeywordList.addKeyword(new Keyword("Doors2"));

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        application.setKeywordList(expectedKeywordList);

        assertEquals(expectedKeywordList, application.getKeywordList());

    }

    @Test
    public void ensureGetCompany() {

        KeywordList expectedKeywordList = new KeywordList();
        expectedKeywordList.addKeyword(new Keyword("Doors2"));

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        String expected = "TESLA";
        String result = application.getCompany();

        assertEquals(expected, result);
    }

    @Test
    public void ensureGetRepresentative() {

        KeywordList expectedKeywordList = new KeywordList();
        expectedKeywordList.addKeyword(new Keyword("Doors2"));

        Representative expected = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(expected, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        Representative result = application.getRepresentative();

        assertEquals(expected, result);
    }

    @Test
    public void ensureGetDecisionList() {

        KeywordList expectedKeywordList = new KeywordList();
        expectedKeywordList.addKeyword(new Keyword("Doors2"));


        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        DecisionList expected = new DecisionList(application);
        application.addKeyword(new Keyword("Doors"));
        DecisionList result = application.getDecisionList();

        assertEquals(expected, result);
    }

    @Test
    public void ensureSetCompanyName() {

        KeywordList expectedKeywordList = new KeywordList();
        expectedKeywordList.addKeyword(new Keyword("Doors2"));


        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        String expected = "SpaceX";
        application.setCompanyName(expected);
        String result = application.getCompany();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEquals() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        assertEquals(application, application);
    }

    @Test
    public void ensureDifferentClassIsDifferent() {
        Keyword key = new Keyword("Doors");

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(key);

        assertNotEquals(application, key);
    }

    @Test
    public void ensureSameApplicationButWithDifferentKeysAreNotEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application2.addKeyword(new Keyword("Doors2"));
        assertNotEquals(application, application2);
    }

    @Test
    public void ensureEqualsNullApplicationIsFalse() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        assertNotEquals(application, null);
    }

    @Test
    public void ensureDifferentDescriptionApplicationButSameKeysAreNotEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application2 = new Application(representative2, "TESLA", "Eletric Car", 10, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        assertNotEquals(application, application2);
    }

    @Test
    public void ensureHashCodeIsCorrect() {

        KeywordList keywords = new KeywordList();
        keywords.addKeyword(new Keyword("Doors"));
        keywords.addKeyword(new Keyword("Windows"));

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 10, 1, keywords);

        int expected = 1198146026;
        int result = application2.hashCode();
        assertEquals(expected, result);

    }

    /**
     * Test of getInvites method, of class Application.
     */
    @Test
    public void testGetInvites() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        int expected = 100;
        int result = application.getInvites();

        assertEquals(expected, result);
    }

    /**
     * Test of setInvites method, of class Application.
     */
    @Test
    public void testSetInvites() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        int expected = 100;
        application.setInvites(100);
        int result = application.getInvites();

        assertEquals(expected, result);

    }

    /**
     * Test of getStandArea method, of class Application.
     */
    @Test
    public void testGetStandArea() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        int expected = 10;
        int result = application.getStandArea();

        assertEquals(expected, result);

    }

    /**
     * Test of setStandArea method, of class Application.
     */
    @Test
    public void testSetStandArea() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        int expected = 10;
        application.setStandArea(10);
        int result = application.getStandArea();

        assertEquals(expected, result);

    }

    /**
     * Test of getStand method, of class Application.
     */
    @Test
    public void testGetStand() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Stand result = application.getStand();
        Stand expected = null;
        assertEquals(expected, result);
    }

    /**
     * Test of setStand method, of class Application.
     */
    @Test
    public void testSetStand() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Stand stand = new Stand(23, "Small stand for quick sell.", 0);

        Stand expected = stand;
        application.setStand(stand);
        Stand result = application.getStand();

        assertEquals(expected, result);
    }
}    