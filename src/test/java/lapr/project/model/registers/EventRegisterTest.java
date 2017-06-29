package lapr.project.model.registers;

import lapr.project.model.*;
import lapr.project.utils.Date.Date;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit testing on the EventRegister class Created by vitor on 05/06/2017.
 */
public class EventRegisterTest {

    /**
     * Test of getSize method, of class EventRegister.
     */
    @Test
    public void testGetSize() {

        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));

        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        List<Event> arrayTest = new ArrayList<>();
        arrayTest.add(event);
        EventRegister registerTest = new EventRegister();
        assertTrue(registerTest.addEvent(event));
        assertFalse(registerTest.addEvent(event));
        int expected = 1;
        int result = registerTest.getSize();

        assertEquals(expected, result);

    }


    @Test
    public void ensureNewExhibitionRegistReturnsSameReference() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event = new Exhibition("event2", "description1", d1, d2, d3, d4, user);
        Event ev = e.newExhibition("event2", "description1", d1, d2, d3, d4, user);

        assertEquals(event, ev);
    }

    @Test
    public void ensureNewEventReturnsCorrectEvent() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event = new Congress("event2", "description1", d1, d2, d3, d4, user);
        Event ev = e.newCongress("event2", "description1", d1, d2, d3, d4, user);

        assertEquals(event, ev);
    }

    @Test
    public void ensureGetEventListReturnsCorrectEvents() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        List<Event> evs = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event ev = e.newCongress("event2", "description1", d1, d2, d3, d4, user);
        Event ev2 = e.newCongress("event1", "description2", d1, d2, d3, d4, user);
        assertTrue(e.addEvent(ev));
        assertTrue(e.addEvent(ev2));
        evs.add(ev);
        evs.add(ev2);

        assertArrayEquals(evs.toArray(), e.getEventList().toArray());
    }

    @Test
    public void ensureGetEventByStateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event ev = e.newCongress("event2", "description1", d1, d2, d3, d4, user);
        Event ev2 = e.newCongress("event1", "description2", d1, d2, d3, d4, user);
        ev.setState(Event.STARTED);
        ev2.setState(Event.NOT_STARTED);
        assertTrue(e.addEvent(ev));
        assertTrue(e.addEvent(ev2));

        List<Event> evs = new ArrayList<>();
        evs.add(ev2);

        assertArrayEquals(e.getEventListByState(Event.NOT_STARTED).toArray(), evs.toArray());
    }

    @Test
    public void ensureGetEvensWithApplicationsOfFAEisCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Event ev = e.newCongress("event2", "description1", d1, d2, d3, d4, user);
        Event ev2 = e.newCongress("event1", "description2", d1, d2, d3, d4, user);

        ev.setState(Event.STARTED_SUBMISSIONS);
        ev2.setState(Event.NOT_STARTED);

        User user0 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);

        Representative representative = new Representative(new User("Jenkins Cunha", "jenkinsWillFail", "jenkins@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());

        ev.getAttributionList().addAttribution(new Attribution(new FAE(user0), application));
        assertTrue(e.addEvent(ev));
        assertTrue(e.addEvent(ev2));

        List<Event> evs = new ArrayList<>();
        evs.add(ev);

        assertArrayEquals(e.getEventListByState(Event.STARTED_SUBMISSIONS).toArray(), evs.toArray());
    }

    /**
     * Test of addEvent method, of class EventRegister.
     */
    @Test
    public void testAddEvent() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        List<Event> arrayTest = new ArrayList<>();
        arrayTest.add(event);
        EventRegister registerTest = new EventRegister();
        registerTest.addEvent(event);

        assertArrayEquals(arrayTest.toArray(), registerTest.getEventsRegister().toArray());
    }

    /**
     * Test of removeEvent method, of class EventRegister.
     */
    @Test
    public void testRemoveEvent() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);

        EventRegister registerTest = new EventRegister();
        assertTrue(registerTest.addEvent(event));
        assertTrue(registerTest.addEvent(event1));
        assertTrue(registerTest.removeEvent(event));
        assertFalse(registerTest.removeEvent(event));
        int expected = 1;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getEventsRegister().contains(event1));

    }

    @Test
    public void testFindEventByID() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);

        EventRegister registerTest = new EventRegister();
        registerTest.addEvent(event);
        registerTest.addEvent(event1);

        assertEquals(event, registerTest.getEvent("event1"));

    }

    /**
     * Test of getEventsRegister method, of class EventRegister.
     */
    @Test
    public void testGetEventsRegister() {

        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);

        EventRegister registerTest = new EventRegister();
        registerTest.addEvent(event);
        registerTest.addEvent(event1);

        int expected = 2;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getEventsRegister().contains(event));
        assertTrue(registerTest.getEventsRegister().contains(event1));

    }

    /**
     * Test if lists with different content are different.
     */
    @Test
    public void ensuresDifferentContentOfListAreNotEqual() {

        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);
        EventRegister expected = new EventRegister();
        EventRegister result = new EventRegister();
        expected.addEvent(event);
        result.addEvent(event1);
        assertNotEquals(expected, result);
    }

    /**
     * Test of equals method, of class EventRegister.
     */
    @Test
    public void ensureSameContentOfListAreEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);
        EventRegister expected = new EventRegister();
        EventRegister result = new EventRegister();
        expected.addEvent(event);
        expected.addEvent(event1);
        result.addEvent(event);
        result.addEvent(event1);

        assertArrayEquals(expected.getEventsRegister().toArray(), result.getEventsRegister().toArray());
    }

    /**
     * Test if a list is different from a null list.
     */
    @Test
    public void ensureNullListAndListAreNotEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);

        EventRegister expected = new EventRegister();
        EventRegister result = null;
        expected.addEvent(event);
        expected.addEvent(event1);

        assertNotEquals(expected, result);
    }

    /**
     * Test if equal lists have the same size.
     */
    @Test
    public void ensureEqualListAreTheSameSize() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE));
        Event event = new Congress("event1", "description1", d1, d2, d3, d4, users);

        Date d5 = new Date(2016, 3, 7);
        Date d6 = new Date(2016, 4, 9);
        Date d7 = new Date(2016, 5, 2);
        Date d8 = new Date(2016, 2, 5);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d5, d6, d7, d8, users2);

        EventRegister test1 = new EventRegister();
        EventRegister test2 = new EventRegister();
        test1.addEvent(event);
        test1.addEvent(event1);
        test2.addEvent(event1);
        test2.addEvent(event);
        int result = test1.getSize();
        int expected = test2.getSize();

        assertEquals(expected, result);
        assertArrayEquals(test1.getEventsRegister().toArray(), test1.getEventsRegister().toArray());
    }

    /**
     * Test of hashCode method, of class EventRegister.
     */
    @Test
    public void testHashCode() {
        Date d1 = new Date();

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event = new Congress("event2", "description2", d1, d1, d1, d1, users);

        OrganizerRegister organizerRegister1 = new OrganizerRegister();
        organizerRegister1.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d1, d1, d1, d1, users2);

        EventRegister registerTest = new EventRegister();
        registerTest.addEvent(event);
        registerTest.addEvent(event1);

        int expected = -1312770132;
        int result = registerTest.hashCode();
        assertEquals(expected, result);
    }

    @Test
    public void testEvenNullHashCode() {
        Date d1 = new Date();

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> users = new ArrayList<>();
        users.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event = new Congress("event2", "description2", d1, d1, d1, d1, users);


        EventRegister registerTest = new EventRegister();
        registerTest.addEvent(event);
        registerTest.addEvent(null);

        int expected = -2041168428;
        int result = registerTest.hashCode();
        assertEquals(expected, result);
    }

    /**
     * Test of newCongress method, of class EventRegister.
     */
    @Test
    public void ensureNewCongressRegistReturnsSameReference() {
        Date d1 = new Date(2017, 7, 12);
        Date d2 = new Date(2017, 7, 14);
        Date d3 = new Date(2017, 2, 2);
        Date d4 = new Date(2017, 8, 2);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("Liliana", "LilianaLopes123", "lilianalopes123@email.com", "password123", User.Language.ENGLISH));
        Event event1 = new Congress("event3", "description2", d1, d2, d3, d4, user);
        Event event2 = e.newCongress("event3", "description2", d1, d2, d3, d4, user);

        assertEquals(event1, event2);
    }

    /**
     * Test of newCongress method, of class EventRegister.
     */
    public void testNewCongress() {
        Date d1 = new Date(2017, 7, 12);
        Date d2 = new Date(2017, 7, 14);
        Date d3 = new Date(2017, 2, 2);
        Date d4 = new Date(2017, 8, 2);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("Liliana", "LilianaLopes123", "lilianalopes123@email.com", "password123", User.Language.ENGLISH));
        Event expected = new Congress("event3", "description2", d1, d2, d3, d4, user);
        EventRegister registerTest = new EventRegister();
        Event result = registerTest.newCongress("event3", "description2", d1, d2, d3, d4, user);
        assertEquals(expected, result);
    }

    /**
     * Test of newExhibition method, of class EventRegister.
     */
    @Test
    public void testNewExhibition() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        EventRegister e = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event expected = new Exhibition("event2", "description1", d1, d2, d3, d4, user);
        EventRegister registerTest = new EventRegister();
        Event result = registerTest.newExhibition("event2", "description1", d1, d2, d3, d4, user);
        assertEquals(expected, result);

    }

    /**
     * Test of getEventList method, of class EventRegister.
     */
    @Test
    public void testGetEventList() {
        Date d1 = new Date(2017, 7, 12);
        Date d2 = new Date(2017, 7, 14);
        Date d3 = new Date(2017, 2, 2);
        Date d4 = new Date(2017, 8, 2);

        EventRegister registerTest = new EventRegister();
        List<User> user = new ArrayList<>();
        user.add(new User("Liliana", "LilianaLopes123", "lilianalopes123@email.com", "password123", User.Language.ENGLISH));
        List<User> users = new ArrayList<>();
        users.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d1, d1, d1, d1, users);
        Event event2 = new Congress("event3", "description2", d1, d2, d3, d4, user);

        registerTest.addEvent(event1);
        registerTest.addEvent(event2);

        int expected = 2;
        int result = registerTest.getSize();
        assertEquals(expected, result);
        assertTrue(registerTest.getEventList().contains(event1));
        assertTrue(registerTest.getEventList().contains(event2));
    }

    /**
     * Test of getEventsNameList method, of class EventRegister.
     */
    @Test
    public void testGetEventsNameList() {
        Date d1 = new Date(2017, 7, 12);
        Date d2 = new Date(2017, 7, 14);
        Date d3 = new Date(2017, 2, 2);
        Date d4 = new Date(2017, 8, 2);

        EventRegister registerTest = new EventRegister();
        List<User> user = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        user.add(new User("Liliana", "LilianaLopes123", "lilianalopes123@email.com", "password123", User.Language.ENGLISH));
        Event event1 = new Congress("event2", "description2", d1, d1, d1, d1, users);
        Event event2 = new Congress("event3", "description2", d1, d2, d3, d4, user);

        registerTest.addEvent(event1);
        registerTest.addEvent(event2);

        List<String> expected = new ArrayList<>();
        expected.add("event2");
        expected.add("event3");

        List<String> result = registerTest.getEventsNameList();

        assertEquals(expected, result);
    }

    /**
     * Ensure empty list gets "No events to show" message
     */
    @Test
    public void ensureEmptyListGetsNoEventsToShowMessage() {
        EventRegister registerTest = new EventRegister();

        List<String> expected = new ArrayList<>();
        expected.add("No events to show");

        List<String> result = registerTest.getEventsNameList();

        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEquals() {
        EventRegister registerTest = new EventRegister();

        assertEquals(registerTest, registerTest);
    }

    @Test
    public void ensureOtherObjectIsNotEquals() {
        EventRegister registerTest = new EventRegister();

        assertNotEquals(registerTest, new Object());
    }

    @Test
    public void ensureFaeWithApplicationsisWorking() {
        EventRegister registerTest = new EventRegister();
        Date d1 = new Date(2017, 7, 12);
        Date d2 = new Date(2017, 7, 14);
        Date d3 = new Date(2017, 2, 2);
        Date d4 = new Date(2017, 8, 2);
        List<String> expected = new ArrayList<>();
        List<User> user = new ArrayList<>();
        user.add(new User("Liliana", "LilianaLopes123", "lilianalopes123@email.com", "password123", User.Language.ENGLISH));
        FAE fae = new FAE(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Event event2 = new Congress("event3", "description2", d1, d2, d3, d4, user);


        Representative representative = new Representative(new User("Luis Cunha2", "luiscunha234", "1151381@isep.ipp.pt", "password", User.Language.PORTUGUESE));
        Application application = new Application(representative, "TESLA", "ELON MUSK FOR THE WIN", 100, 10, new KeywordList());
        application.addKeyword(new Keyword("Doors"));

        event2.getAttributionList().addAttribution(new Attribution(fae, application));
        event2.getApplicationList().addApplication(application);

        registerTest.addEvent(event2);
        List<Event> result = registerTest.getFaesEventsWithApplications(fae);
        assertEquals(result.size(), 1);
        assertEquals(event2, result.get(0));
    }
}
