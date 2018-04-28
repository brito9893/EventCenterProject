package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lilyl
 */
public class UserRegisterTest {

    /**
     * Test of size method, of class UserRegister.
     */
    @Test
    public void testGetSize() {
        User user = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        List<User> arrayTest = new ArrayList<>();
        arrayTest.add(user);
        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);

        int expected = 1;
        int result = registerTest.getSize();

        assertEquals(expected, result);

    }

    /**
     * Test of addUser method, of class UserRegister.
     */
    @Test
    public void testAddUser() {
        User user = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        List<User> arrayTest = new ArrayList<>();
        arrayTest.add(user);
        UserRegister registerTest = new UserRegister();
        assertTrue(registerTest.addUser(user));
        assertFalse(registerTest.addUser(user));
        assertArrayEquals(arrayTest.toArray(), registerTest.getUsersList().toArray());
    }

    /**
     * Test of removeUser method, of class UserRegister.
     */
    @Test
    public void testRemoveUser() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);
        assertTrue(registerTest.removeUser(user));
        assertFalse(registerTest.removeUser(user));

        int expected = 1;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getUsersList().contains(user1));

    }

    @Test
    public void testGetUserByNameAndUserName() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);


        User result = registerTest.getUserByName("Luis Cunha");
        assertEquals(user, result);


        User result2 = registerTest.getUserByUserName("lilianalopes198");
        assertEquals(user1, result2);

        assertNull(registerTest.getUserByName(null));
        assertNull(registerTest.getUserByUserName(null));
    }


    /**
     * Test of getUsersList method, of class UserRegister.
     */
    @Test
    public void testGetUsersList() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);

        int expected = 2;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getUsersList().contains(user));
        assertTrue(registerTest.getUsersList().contains(user1));

    }

    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@teste.pt", "password321", User.Language.PORTUGUESE);
        UserRegister expected = new UserRegister();
        UserRegister result = new UserRegister();
        expected.addUser(user0);
        result.addUser(user1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class UserRegister.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@teste.pt", "password321", User.Language.PORTUGUESE);
        UserRegister expected = new UserRegister();
        UserRegister result = new UserRegister();
        expected.addUser(user0);
        expected.addUser(user1);
        result.addUser(user1);
        result.addUser(user0);

        assertEquals(expected, result);
    }

    @Test
    public void ensureNullListAndListAreNotEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@teste.pt", "password321", User.Language.PORTUGUESE);
        UserRegister expected = new UserRegister();
        UserRegister result = null;
        expected.addUser(user0);
        expected.addUser(user1);

        assertNotEquals(expected, result);
    }

    @Test
    public void ensureEqualListAreTheSameSize() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@teste.pt", "password321", User.Language.PORTUGUESE);
        UserRegister teste1 = new UserRegister();
        UserRegister teste2 = new UserRegister();
        teste1.addUser(user0);
        teste1.addUser(user1);
        teste2.addUser(user1);
        teste2.addUser(user0);
        int result = teste1.getSize();
        int expected = teste2.getSize();

        assertEquals(expected, result);
    }

    @Test
    public void testSameObjectAreEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);


        assertEquals(registerTest, registerTest);


    }

    @Test
    public void testObjectIsNotEqualtToUserRegist() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Liliana Lopes", "lilianalopes198", "1161560@isep.ipp.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);


        assertNotEquals(registerTest, new Object());


    }

    /**
     * Test of hashCode method, of class UserRegister.
     */
    @Test
    public void testHashCode() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@teste.pt", "password321", User.Language.PORTUGUESE);

        UserRegister registerTest = new UserRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);
        int expected = 1362916934;
        int result = registerTest.hashCode();
        assertEquals(expected, result);
    }

    /**
     * Ensure empty list gets "No users to show" message
     */
    @Test
    public void ensureEmptyListGetsNoUsersToShowMessage() {
        UserRegister userRegister = new UserRegister();

        List<String> expected = new ArrayList<>();
        expected.add("No users to show");

        List<String> result = userRegister.getUsersNameList();

        assertEquals(expected, result);
    }
}
