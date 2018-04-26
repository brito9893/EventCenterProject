package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Organizer;
import lapr.project.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mário Vaz
 */
public class OrganizerRegisterTest {

    /**
     * Test of getSize method, of class OrganizerRegister.
     */
    @Test
    public void testGetSize() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        List<Organizer> arrayTest = new ArrayList<>();
        arrayTest.add(organizer);
        OrganizerRegister listTest = new OrganizerRegister();
        listTest.addOrganizer(organizer);

        int expected = 1;
        int result = listTest.getSize();

        assertEquals(expected, result);

    }

    /**
     * Test of addOrganizer method, of class OrganizerRegister.
     */
    @Test
    public void testAddOrganizer() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        List<Organizer> arrayTest = new ArrayList<>();
        arrayTest.add(organizer);
        OrganizerRegister listTest = new OrganizerRegister();
        listTest.addOrganizer(organizer);

        assertArrayEquals(arrayTest.toArray(), listTest.getOrganizersList().toArray());
    }

    @Test
    public void testAddSameOrganizer() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        OrganizerRegister listTest = new OrganizerRegister();
        assertTrue(listTest.addOrganizer(organizer));
        assertFalse(listTest.addOrganizer(organizer));

        int result = 1;
        int expected = listTest.getSize();
        assertEquals(expected, result);

    }

    /**
     * Test of removeOrganizer method, of class OrganizerRegister.
     */
    @Test
    public void testRemoveOrganizer() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        OrganizerRegister listTest = new OrganizerRegister();
        listTest.addOrganizer(organizer);
        listTest.addOrganizer(organizer1);
        listTest.removeOrganizer(organizer);
        int expected = 1;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getOrganizersList().contains(organizer1));

    }

    /**
     * Test of getOrganizersList method, of class OrganizerRegister.
     */
    @Test
    public void testGetOrganizersList() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        OrganizerRegister listTest = new OrganizerRegister();
        listTest.addOrganizer(organizer);
        listTest.addOrganizer(organizer1);

        int expected = 2;
        int result = listTest.getSize();
        assertEquals(expected, result);
        assertTrue(listTest.getOrganizersList().contains(organizer));
        assertTrue(listTest.getOrganizersList().contains(organizer1));

    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        Organizer organizer0 = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        OrganizerRegister expected = new OrganizerRegister();
        OrganizerRegister result = new OrganizerRegister();
        expected.addOrganizer(organizer0);
        result.addOrganizer(organizer1);
        assertNotEquals(expected, result);
    }


    /**
     * Test of equals method, of class OrganizerRegister.
     */
    @Test
    public void ensureSameListAreEqual() {
        Organizer organizer0 = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        OrganizerRegister expected = new OrganizerRegister();

        expected.addOrganizer(organizer0);
        expected.addOrganizer(organizer1);


        assertArrayEquals(expected.getOrganizersList().toArray(), expected.getOrganizersList().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        Organizer organizer0 = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        OrganizerRegister expected = new OrganizerRegister();
        expected.addOrganizer(organizer0);
        expected.addOrganizer(organizer1);

        assertNotEquals(expected, null);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        Organizer organizer0 = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));
        OrganizerRegister test1 = new OrganizerRegister();
        OrganizerRegister test2 = new OrganizerRegister();
        test1.addOrganizer(organizer0);
        test1.addOrganizer(organizer1);
        test2.addOrganizer(organizer1);
        test2.addOrganizer(organizer0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getOrganizersList().toArray(), test1.getOrganizersList().toArray());
    }

    /**
     * Test of hashCode method, of class OrganizerRegister.
     */
    @Test
    public void testHashCode() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));
        Organizer organizer1 = new Organizer(new User("nome2", "userName2", "userName2@email.com", "password2", User.Language.ENGLISH));

        OrganizerRegister listTest = new OrganizerRegister();
        listTest.addOrganizer(organizer);
        listTest.addOrganizer(organizer1);

        int expected = -2093634730;
        int result = listTest.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void testHashCodeWithNull() {
        Organizer organizer = new Organizer(new User("nome1", "userName1", "userName1@email.com", "password1", User.Language.PORTUGUESE));


        OrganizerRegister nullTest = new OrganizerRegister();

        nullTest.addOrganizer(organizer);
        nullTest.addOrganizer(null);

        int expected = 115238606;

        assertEquals(expected, nullTest.hashCode());
    }
}
