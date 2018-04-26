package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz on 06-Jun-17.
 */
public class ApplicationListTest {

    /**
     * Test of getSize method, of class ApplicationList.
     */
    @Test
    public void testGetSize() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        listTest.addApplication(application);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);
    }

    /**
     * Test of addApplication method, of class ApplicationList.
     */
    @Test
    public void testAddApplication() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));

        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        List<Application> arrayTest = new ArrayList<>();

        assertTrue(arrayTest.add(application));

        ApplicationList listTest = new ApplicationList();

        assertTrue(listTest.addApplication(application));
        assertFalse(listTest.addApplication(application));

        assertArrayEquals(arrayTest.toArray(), listTest.getApplicationList().toArray());
    }

    /**
     * Test of removeApplication method, of class ApplicationList.
     */
    @Test
    public void testRemoveApplication() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        Application result = listTest.createApplication(representative, "TESLA2", "ELON MUSK FOR THE WIN2", 102, 12, new KeywordList());
        result.addKeyword(new Keyword("Doors2"));
        assertTrue(listTest.addApplication(application));
        assertTrue(listTest.addApplication(result));
        assertTrue(listTest.removeApplication(application));
        assertFalse(listTest.removeApplication(application));
        int expected = 1;
        assertEquals(expected, listTest.getSize());

    }

    @Test
    public void testCreateApplication() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        Application result = listTest.createApplication(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        result.addKeyword(new Keyword("Doors"));

        assertEquals(application, result);

    }

    /**
     * Test of getApplicationList method, of class ApplicationList.
     */
    @Test
    public void testGetApplication() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Application application1 = new Application(representative, "TESLA2", "ELON MUSK FOR THE WIN2", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        listTest.addApplication(application);
        listTest.addApplication(application1);

        Application app = listTest.getApplication("TESLA");
        Application app2 = listTest.getApplication("TESLA2");
        assertEquals(application, app);
        assertEquals(application1, app2);
    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application0 = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application0.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList expected = new ApplicationList();
        ApplicationList result = new ApplicationList();
        expected.addApplication(application0);
        result.addApplication(application1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class ApplicationList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application0 = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application0.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList expected = new ApplicationList();
        ApplicationList result = new ApplicationList();
        expected.addApplication(application0);
        expected.addApplication(application1);
        result.addApplication(application0);
        result.addApplication(application1);

        assertArrayEquals(expected.getApplicationList().toArray(), result.getApplicationList().toArray());
    }

    @Test
    public void ensureSameListIsEquals() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application0 = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application0.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList expected = new ApplicationList();
        expected.addApplication(application0);
        expected.addApplication(application1);


        assertEquals(expected, expected);
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application0 = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application0.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList expected = new ApplicationList();
        ApplicationList result = null;
        expected.addApplication(application0);
        expected.addApplication(application1);

        assertNotEquals(expected, result);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application0 = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application0.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList test1 = new ApplicationList();
        ApplicationList test2 = new ApplicationList();
        test1.addApplication(application0);
        test1.addApplication(application1);
        test2.addApplication(application1);
        test2.addApplication(application0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getApplicationList().toArray(), test1.getApplicationList().toArray());
    }

    /**
     * Test of hashCode method, of class ApplicationList.
     */
    @Test
    public void testHashCode() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        listTest.addApplication(application);
        listTest.addApplication(application1);

        int expected = 760470032;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void getAcceptedApplications() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        assertTrue(application.getDecisionList().addDecision(new Decision(new ArrayList<>(), true, "", new FAE(representative.getUser()))));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));

        ApplicationList listTest = new ApplicationList();
        listTest.addApplication(application);
        listTest.addApplication(application1);

        int expected = 1;
        int result = listTest.getNumberOfAcceptedApplications();
        assertEquals(expected, result);
    }


    @Test
    public void getKeywords() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));
        assertTrue(application.getDecisionList().addDecision(new Decision(new ArrayList<>(), true, "", new FAE(representative.getUser()))));

        Representative representative2 = new Representative(new User("Luis Cunha123", "luiscunha2334", "11513581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application1 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN123", 100, 10, new KeywordList());
        application1.addKeyword(new Keyword("Doors"));
        application1.addKeyword(new Keyword("Banana"));

        ApplicationList listTest = new ApplicationList();
        listTest.addApplication(application);
        listTest.addApplication(application1);

        Map<String, Double> map = listTest.getKeywords();
        double expectedDoors = 2.0 / 3.0;
        double expectedBanana = 1.0 / 3.0;
        double resultDoors = map.get("Doors");
        double resultBanana = map.get("Banana");
        assertTrue(expectedDoors == resultDoors);
        assertTrue(expectedBanana == resultBanana);
    }

    /**
     * Ensure empty list gets "No applications to show" message
     */
    @Test
    public void ensureEmptyListGetsNoApplicationsToShowMessage() {
        ApplicationList applicationList = new ApplicationList();

        List<String> expected = new ArrayList<>();
        expected.add("No applications to show");

        List<String> result = applicationList.getApplicationNameList();

        assertEquals(expected, result);
    }
}
