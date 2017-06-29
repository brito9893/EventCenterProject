/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.registers.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Luis Cunha 06/06/2017
 */
public class EventCenterTest {

    @Test
    public void ensuresNewEventCenterReturnsEmptyRegisters() {
        EventCenter center = new EventCenter();
        EventManagerRegister mang = new EventManagerRegister();
        UserRegister users = new UserRegister();
        User user = new User("DARKFORCE", "darkforce", "darkforce@isep.ipp.pt", "123", User.Language.ENGLISH);
        users.addUser(user);
        mang.addUser(user);

        assertEquals(center.getEventManagers(), mang);
        assertEquals(center.getEventRegister(), new EventRegister());
        assertEquals(center.getUserNotConfirmedRegister(), new UserNotConfirmedRegister());
        assertEquals(center.getUserRegister(), users);
    }

    /**
     * Test of getUserRegister method, of class EventCenter.
     */
    @Test
    public void ensureGetUserRegisterIsCorrect() {
        EventCenter center = new EventCenter();
        UserRegister expected = new UserRegister();
        expected.addUser(new User("DARKFORCE", "darkforce", "darkforce@isep.ipp.pt", "123", User.Language.ENGLISH));
        UserRegister result = center.getUserRegister();
        assertEquals(expected, result);
    }

    /**
     * Test of getEventRegister method, of class EventCenter.
     */
    @Test
    public void ensureGetEventRegisterIsCorrect() {
        EventCenter center = new EventCenter();
        EventRegister expected = new EventRegister();
        EventRegister result = center.getEventRegister();
        assertEquals(expected, result);
    }

    /**
     * Test of getUserNotConfirmedRegister method, of class EventCenter.
     */
    @Test
    public void ensureGetUserNotConfirmedRegisterIsCorrect() {
        EventCenter center = new EventCenter();
        UserNotConfirmedRegister expected = new UserNotConfirmedRegister();
        UserNotConfirmedRegister result = center.getUserNotConfirmedRegister();
        assertEquals(expected, result);
    }

    @Test
    public void ensuresEqualsToItSelf() {
        EventCenter center = new EventCenter();

        assertEquals(center, center);
    }

    @Test
    public void ensuresNotEqualsToNull() {
        EventCenter center = new EventCenter();
        EventCenter centerNull = null;

        assertNotEquals(center, centerNull);
    }

    @Test
    public void ensuresNotEqualsToOtherClassObject() {
        EventCenter center = new EventCenter();
        Event event = new Congress();

        assertNotEquals(center, event);
    }

    @Test
    public void ensuresNotEqualsToNotEmplyEventCenter() {
        EventCenter center = new EventCenter();
        EventCenter center2 = new EventCenter();
        center2.getEventRegister().addEvent(new Congress());
        assertNotEquals(center, center2);

        EventCenter center3 = new EventCenter();
        center3.getUserRegister().addUser(new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        assertNotEquals(center, center3);

        EventCenter center4 = new EventCenter();
        center4.getUserNotConfirmedRegister().addUser(new User());
        assertNotEquals(center, center4);
    }

    @Test
    public void ensureHashCodeIsCorrect() {
        EventCenter center = new EventCenter();
        int expected = -479573832;
        int result = center.hashCode();
        assertEquals(expected, result);
    }
}
