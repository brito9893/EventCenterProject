/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.User.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * User Unit Testing.
 *
 * @author Luis Cunha
 */
public class UserTest {

    @Test
    public void ensureGetEmailIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        String expected = "1151581@isep.ipp.pt";
        String result = user.getEmail();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetPasswordIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        String expected = "password";
        assertTrue(user.isPasswordCorrect(expected));
    }

    @Test
    public void ensureGetUserNameIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        String expected = "luiscunha233";
        String result = user.getUserName();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetNameIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        String expected = "Luis Cunha";
        String result = user.getName();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetLanguageIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Language expected = User.Language.PORTUGUESE;
        Language result = user.getLanguage();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User result = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureSameUserWithSameEmailAndUsernameAreEqual() {
        User expected = new User("Luisa Leite", "luiscunha233", "1151581@isep.ipp.pt", "password321", User.Language.ENGLISH);
        User result = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertEquals(expected, result);
    }

    @Test
    public void ensureUserWithDiferrentEmailAndUsernameAreNotEqual() {
        User expected = new User("Luisa Leite", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User result = new User("Luis Cunha", "luiscunha10", "luispt233@gmail.com", "password", User.Language.PORTUGUESE);
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Object result = new Object();
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureNullObjectAreNotEqual() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        Object result = null;
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);

        int expected = -1942644601;
        int result = user.hashCode();
        assertEquals(expected, result);
    }
}
