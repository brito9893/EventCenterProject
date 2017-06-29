/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.registers;

import lapr.project.model.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author U
 */
public class EventManagerRegisterTest {

    @Test
    public void testGetSizeAndAddUser() {
        EventManagerRegister regist = new EventManagerRegister();

        assertTrue(regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));
        assertTrue(regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE)));
        assertFalse(regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE)));
        int expected = 2;
        int result = regist.getSize();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveUser() {
        EventManagerRegister regist = new EventManagerRegister();

        assertTrue(regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));
        assertTrue(regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE)));
        assertTrue(regist.removeUser(new EventManager(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE))));
        assertFalse(regist.removeUser(new EventManager(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE))));
        int expected = 1;
        int result = regist.getSize();

        assertEquals(expected, result);
    }

    @Test
    public void testSameObjectIsEqual() {
        EventManagerRegister regist = new EventManagerRegister();

        regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));


        assertEquals(regist, regist);
    }

    @Test
    public void testObjectNullIsNotEqual() {
        EventManagerRegister regist = new EventManagerRegister();

        regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));


        assertNotEquals(regist, null);
    }

    @Test
    public void testOtherClassIsNotEqual() {
        EventManagerRegister regist = new EventManagerRegister();

        EventManager event = new EventManager(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));

        regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));

        assertNotEquals(regist, event);

    }

    @Test
    public void testDifferenteSizesAreNotEqual() {
        EventManagerRegister regist = new EventManagerRegister();
        EventManagerRegister regist1 = new EventManagerRegister();
        EventManager event = new EventManager(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));

        regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));

        regist1.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));


        assertNotEquals(regist, regist1);

    }

    @Test
    public void testDifferenteContentAreNotEqual() {
        EventManagerRegister regist = new EventManagerRegister();
        EventManagerRegister regist1 = new EventManagerRegister();


        regist.addUser(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        regist.addUser(new User("user2", "userName2", "user2@email.com", "password", User.Language.PORTUGUESE));

        regist1.addUser(new User("user3", "userName3", "user3@email.com", "password", User.Language.PORTUGUESE));
        regist1.addUser(new User("user4", "userName4", "user4@email.com", "password", User.Language.PORTUGUESE));

        assertNotEquals(regist, regist1);

    }

}
