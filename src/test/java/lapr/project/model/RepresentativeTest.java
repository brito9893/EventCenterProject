/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Luís Cunha at 11/06/2017
 */
public class RepresentativeTest {

    @Test
    public void ensureGetUserIsCorrect() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative org = new Representative(expected);
        User result = org.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void ensureNullObjectAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative expected = new Representative(user);
        Object result = null;
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative expected = new Representative(user0);
        Representative result = new Representative(user1);
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative expected = new Representative(user);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative expected = new Representative(user);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative organizor = new Representative(user);
        int expected = -1942644260;
        int result = organizor.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void ensureEqualsWorks() {
        User o1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User o2 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Representative u = new Representative(o1);
        Representative u2 = new Representative(o2);

        boolean actual = u.equals(u2);

        assertTrue(actual);
    }


}
