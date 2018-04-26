/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.registers.OrganizerRegister;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Organizers Unit Tests
 *
 * @author Luis Cunha on 01/06/2017
 */
public class OrganizerTest {


    @Test
    public void ensureGetUserIsCorrect() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer org = new Organizer(expected);
        User result = org.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetUserReturnsSameReference() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer organizor = new Organizer(expected);
        User result = organizor.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void ensureNullObjectAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer expected = new Organizer(user);
        Object result = null;
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer expected = new Organizer(user0);
        Organizer result = new Organizer(user1);
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer expected = new Organizer(user);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer expected = new Organizer(user);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer organizor = new Organizer(user);
        int expected = -1942644384;
        int result = organizor.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetOrganizersListReturnsSameReference() {
        OrganizerRegister o = new OrganizerRegister();

        List<Organizer> e = new ArrayList<>();
        String expected = e.getClass().toGenericString();
        List<Organizer> a = o.getOrganizersList();
        String actual = a.getClass().toGenericString();

        assertEquals(expected, actual);

    }

    @Test
    public void ensureGetSizeReturnsCorrectSize() {
        OrganizerRegister o = new OrganizerRegister();
        o.addOrganizer(new Organizer(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE)));

        int expected = 1;
        int actual = o.getSize();

        assertEquals(expected, actual);
    }

    @Test
    public void ensureRemoveOrganizerWorks() {
        OrganizerRegister o = new OrganizerRegister();
        o.addOrganizer(new Organizer(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE)));

        User o1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer o2 = new Organizer(o1);

        o.removeOrganizer(o2);

        int expected = 0;
        int actual = o.getSize();

        assertEquals(expected, actual);

    }

    @Test
    public void ensureEqualsWorks() {
        User o1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User o2 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer u = new Organizer(o1);
        Organizer u2 = new Organizer(o2);

        boolean actual = u.equals(u2);

        assertTrue(actual);
    }

    @Test
    public void ensureHasOrganizerWorks() {
        OrganizerRegister o = new OrganizerRegister();
        User o1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Organizer u = new Organizer(o1);
        o.addOrganizer(u);

        boolean actual = o.hasOrganizer(u);

        assertTrue(actual);
    }
}
