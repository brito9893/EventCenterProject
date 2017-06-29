/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.registers;

import lapr.project.model.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Classe de Teste.
 *
 * @author Luís Cunha on 02/06/2017
 */


public class UserNotConfirmedRegisterTest {

    @Test
    public void ensuresNewUserIsCorrect() {
        User expected = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();
        User result = registerTest.newUser("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertEquals(expected, result);
    }

    @Test
    public void ensuresAddUserIsAddingToList() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        List<User> arrayTest = new ArrayList<>();
        assertTrue(arrayTest.add(user));
        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();
        registerTest.addUser(user);

        assertArrayEquals(arrayTest.toArray(), registerTest.getUserNotConfirmed().toArray());
    }

    @Test
    public void ensuresGetUserNotConfirmedRegistIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        List<User> arrayTest = new ArrayList<>();
        arrayTest.add(user);
        arrayTest.add(user1);
        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);

        assertArrayEquals(arrayTest.toArray(), registerTest.getUserNotConfirmed().toArray());
    }

    @Test
    public void ensuresGetSizeIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);

        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();

        registerTest.addUser(user);
        registerTest.addUser(user1);

        int expected = 2;
        int result = registerTest.getSize();

        assertEquals(expected, result);
    }

    @Test
    public void ensuresAddUserIsNotAddingDuplicates() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();
        assertTrue(registerTest.addUser(user));
        assertFalse(registerTest.addUser(user1));
        int expected = 1;
        int result = registerTest.getSize();
        assertEquals(result, expected);
    }

    @Test
    public void ensuresRemoveUserIsCorrect() {
        User user = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);

        UserNotConfirmedRegister registerTest = new UserNotConfirmedRegister();
        registerTest.addUser(user);
        registerTest.addUser(user1);
        assertTrue(registerTest.removeUser(user));
        int expected = 1;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getUserNotConfirmed().contains(user1));
    }


    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();
        UserNotConfirmedRegister result = new UserNotConfirmedRegister();
        expected.addUser(user0);
        result.addUser(user1);
        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameContentOfListAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();
        UserNotConfirmedRegister result = new UserNotConfirmedRegister();
        expected.addUser(user0);
        expected.addUser(user1);
        result.addUser(user1);
        result.addUser(user0);

        assertEquals(expected, result);
    }

    @Test
    public void ensureNullListAndListAreNotEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();
        expected.addUser(user0);
        expected.addUser(user1);

        assertNotEquals(expected, null);
    }

    @Test
    public void ensureEqualListAreTheSameSize() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister test1 = new UserNotConfirmedRegister();
        UserNotConfirmedRegister test2 = new UserNotConfirmedRegister();
        test1.addUser(user0);
        test1.addUser(user1);
        test2.addUser(user1);
        test2.addUser(user0);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
    }

    @Test
    public void ensureDifferentObjectTypeAreNotEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User();
        UserNotConfirmedRegister test1 = new UserNotConfirmedRegister();
        test1.addUser(user0);

        assertNotEquals(user1, test1);
    }


    @Test
    public void ensureDifferentListArentTheSameSize() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister test1 = new UserNotConfirmedRegister();
        UserNotConfirmedRegister test2 = new UserNotConfirmedRegister();
        test1.addUser(user0);
        test1.addUser(user1);
        test2.addUser(user1);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertNotEquals(expected, result);
    }

    @Test
    public void ensureSameListAreEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();


        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentClassesAreNotEqual() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();


        assertEquals(expected, expected);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        User user1 = new User("Joao Silva", "joaosilva11", "joaosilva@test.pt", "password321", User.Language.PORTUGUESE);
        UserNotConfirmedRegister test1 = new UserNotConfirmedRegister();

        test1.addUser(user0);
        test1.addUser(user1);

        int result = test1.hashCode();
        int expected = -1244737439;
        assertEquals(expected, result);
    }

}
