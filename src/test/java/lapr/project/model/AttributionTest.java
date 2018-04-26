package lapr.project.model;

import lapr.project.model.registers.KeywordList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Mário Vaz
 */
public class AttributionTest {

    /**
     * Test of getFae method, of class Attribution.
     */
    @Test
    public void ensureGetFaeIsCorrect() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        FAE expected = new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        FAE result = attribution.getFae();
        assertEquals(expected, result);
    }

    /**
     * Test of createApplication method, of class Attribution.
     */
    @Test
    public void ensureGetApplicationIsCorrect() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Application expected = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());


        Application result = attribution.getApplication();
        assertEquals(expected, result);
    }

    /**
     * Test if attributions with same content are equal.
     */
    @Test
    public void ensureSameContentObjectsAreEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        Attribution result = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        assertEquals(expected, result);
    }

    /**
     * Test if a attribution is equal to itself.
     */
    @Test
    public void ensureSameObjectIsEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        assertEquals(expected, expected);
    }

    /**
     * Test if attribution with the same FAE but different Application are different.
     */
    @Test
    public void ensureSameAttributionWithSameFAEAndDifferentApplicationAreNotEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "SPACEX", "ELON MUSK TO MARS", 20, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        Attribution result = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app2);
        assertNotEquals(expected, result);
    }

    /**
     * Test if attribution with the same Application but different FAE are different.
     */
    @Test
    public void ensureSameAttributionWithSameApplicationAndDifferentFAEAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        Attribution result = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app);
        assertNotEquals(expected, result);
    }

    /**
     * Test if attribution with different FAE and Application are different.
     */
    @Test
    public void ensureAttributionWithDifferentFAEAndApplicationAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        Attribution result = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);
        assertNotEquals(expected, result);
    }

    /**
     * Test if a attribution and an Object are different.
     */
    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    /**
     * Test if a attribution and a null Object are different.
     */
    @Test
    public void ensureNullObjectAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution expected = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        assertNotEquals(expected, null);
    }

    /**
     * Test of hashCode method, of class Attribution.
     */
    @Test
    public void ensureHashCodeIsCorrect() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        int expected = -1405372107;
        int result = attribution.hashCode();
        assertEquals(expected, result);
    }
}
