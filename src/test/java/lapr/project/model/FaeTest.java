/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit Test for the FAE Class.
 *
 * @author Luis Cunha on 01/06/2017
 */
public class FaeTest {

    @Test
    public void ensureGetUserIsCorrect() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE fae = new FAE(expected);
        User result = fae.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE expected = new FAE(user0);
        FAE result = new FAE(user1);

        assertEquals(expected, result);
    }

    @Test
    public void ensureNullObjectAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE expected = new FAE(user);
        Object result = null;
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE expected = new FAE(user);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE expected = new FAE(user);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        FAE organizor = new FAE(user);
        int expected = -1942644416;
        int result = organizor.hashCode();
        assertEquals(expected, result);
    }

}
