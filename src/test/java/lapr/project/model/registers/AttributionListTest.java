package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class AttributionListTest {
    /**
     * Test of size method, of class AttributionList.
     */
    @Test
    public void testGetSize() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        List<Attribution> arrayTest = new ArrayList<>();
        arrayTest.add(attribution);
        AttributionList listTest = new AttributionList();
        listTest.addAttribution(attribution);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);

    }

    /**
     * Test of addAttribution method, of class AttributionList.
     */
    @Test
    public void testAddAttribution() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);
        List<Attribution> arrayTest = new ArrayList<>();
        arrayTest.add(attribution);
        AttributionList listTest = new AttributionList();
        listTest.addAttribution(attribution);

        assertArrayEquals(arrayTest.toArray(), listTest.getAttributionsList().toArray());
    }

    /**
     * Test of removeAttribution method, of class AttributionList.
     */
    @Test
    public void testRemoveAttribution() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList listTest = new AttributionList();
        listTest.addAttribution(attribution);
        listTest.addAttribution(attribution1);
        listTest.removeAttribution(attribution);
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getAttributionsList().contains(attribution1));

    }

    /**
     * Test of getAttributionsList method, of class AttributionList.
     */
    @Test
    public void testGetAttributionsList() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList listTest = new AttributionList();
        listTest.addAttribution(attribution);
        listTest.addAttribution(attribution1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getAttributionsList().contains(attribution));
        assertTrue(listTest.getAttributionsList().contains(attribution1));

    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution0 = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList expected = new AttributionList();
        AttributionList result = new AttributionList();
        expected.addAttribution(attribution0);
        result.addAttribution(attribution1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class AttributionList.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution0 = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList expected = new AttributionList();
        AttributionList result = new AttributionList();
        expected.addAttribution(attribution0);
        expected.addAttribution(attribution1);
        result.addAttribution(attribution0);
        result.addAttribution(attribution1);

        assertArrayEquals(expected.getAttributionsList().toArray(), result.getAttributionsList().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution0 = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList expected = new AttributionList();
        expected.addAttribution(attribution0);
        expected.addAttribution(attribution1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution0 = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList test1 = new AttributionList();
        AttributionList test2 = new AttributionList();
        test1.addAttribution(attribution0);
        test1.addAttribution(attribution1);
        test2.addAttribution(attribution1);
        test2.addAttribution(attribution0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getAttributionsList().toArray(), test1.getAttributionsList().toArray());
    }

    /**
     * Test of hashCode method, of class AttributionList.
     */
    @Test
    public void testHashCode() {
        Representative representative = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution = new Attribution(new FAE(new User("name1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)), app);

        Representative representative2 = new Representative(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application app2 = new Application(representative2, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        Attribution attribution1 = new Attribution(new FAE(new User("name2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)), app2);

        AttributionList listTest = new AttributionList();
        listTest.addAttribution(attribution);
        listTest.addAttribution(attribution1);

        int expected = 1523749730;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }
}
