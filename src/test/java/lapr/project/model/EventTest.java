package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.registers.ApplicationList;
import lapr.project.model.registers.FAEList;
import lapr.project.model.registers.OrganizerRegister;
import lapr.project.model.registers.StandList;
import lapr.project.utils.Date.Date;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Event class and subclasses tests Created by Mário Vaz on 07-Jun-17.
 */
public class EventTest extends Congress {

    @Test
    public void ensureGetCongressNameIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);

        String expected = "event";
        String result = event.getEventName();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetDescriptionIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description1", d1, d2, d3, d4, user);

        String expected = "description1";
        String result = event.getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetStartDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2017, 6, 9);
        Date result = event.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetEndDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2017, 6, 10);
        Date result = event.getEndDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetSubmissionStartDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2017, 1, 1);
        Date result = event.getSubmissionStartDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetSubmissionEndDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2017, 6, 1);
        Date result = event.getSubmissionEndDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetOrganizerRegisterIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        OrganizerRegister expected = new OrganizerRegister();
        expected.addOrganizer(new Organizer(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH)));

        OrganizerRegister result = event.getOrganizerRegister();
        assertEquals(expected, result);
    }

    @Test
    public void ensureGetFAEListIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        FAEList expected = new FAEList();

        FAEList result = event.getFaeList();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetCongressNameIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        String expected = "event2";
        event.setEventName("event2");
        String result = event.getEventName();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetDescriptionIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        String expected = "description2";
        event.setDescription("description2");
        String result = event.getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetStartDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2018, 6, 9);
        event.setStartDate(new Date(2018, 6, 9));
        Date result = event.getStartDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetEndDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2018, 6, 10);
        event.setEndDate(new Date(2018, 6, 10));
        Date result = event.getEndDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetSubmissionStartDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2018, 1, 1);
        event.setSubmissionStartDate(new Date(2018, 1, 1));
        Date result = event.getSubmissionStartDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSetSubmissionEndDateIsCorrect() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        OrganizerRegister organizerRegister = new OrganizerRegister();
        organizerRegister.addOrganizer(new Organizer(new User("user1", "userName1", "user1@email.com", "password1", User.Language.PORTUGUESE)));

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        Date expected = new Date(2018, 6, 1);
        event.setSubmissionEndDate(new Date(2018, 6, 1));
        Date result = event.getSubmissionEndDate();
        assertEquals(expected, result);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Congress expected = new Congress("event", "description1", d1, d2, d3, d4, user);
        Congress result = new Congress("event", "description1", d1, d2, d3, d4, user);

        assertEquals(expected, result);
    }

    @Test
    public void ensureSameObjectIsEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Congress expected = new Congress("event", "description1", d1, d2, d3, d4, user);
        assertEquals(expected, expected);
    }

    @Test
    public void ensureDifferentObjectsAreNotEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Congress expected = new Congress("event", "description1", d1, d2, d3, d4, user);

        Object result = new Object();
        assertNotEquals(expected, result);
    }

    /**
     * Test if an event and a null Object are different.
     */
    @Test
    public void ensureNullObjectAreNotEqual() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Congress expected = new Congress("event", "description1", d1, d2, d3, d4, user);

        assertNotEquals(expected, null);
    }

    /**
     * Test of hashCode method, of class Congress.
     */
    @Test
    public void ensureHashCodeIsCorrect() {
        Date d1 = new Date();

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d1, d1, d1, user);

        int expected = -1312770349;
        int result = event.hashCode();
        assertEquals(expected, result);
    }


    @Test
    public void ensureEventClonerConstructor() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress(event);

        assertEquals(event, event2);

        Exhibition ex = new Exhibition("event", "description2", d1, d2, d3, d4, user);
        Exhibition ex2 = new Exhibition(ex);
        assertEquals(ex, ex2);
    }

    @Test
    public void ensureStatesIsWorking() {
        String[] states = Event.getEventStates();

        assertEquals(Event.NOT_STARTED, states[0]);
        assertEquals(Event.STARTED, states[1]);
        assertEquals(Event.NOT_STARTED_SUBMISSIONS, states[2]);
        assertEquals(Event.STARTED_SUBMISSIONS, states[3]);
        assertEquals(Event.ENDED_SUBMISSIONS, states[4]);
        assertEquals(Event.SUBMISSIONS_ON_DECISION, states[5]);

        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));

        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);

        event.setState("UNKNOWN");

        assertEquals(event.getState(), Event.NOT_STARTED);

        event.setState(Event.SUBMISSIONS_ON_DECISION);
        assertEquals(event.getState(), Event.SUBMISSIONS_ON_DECISION);

    }

    @Test
    public void testAddOrganizor() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        User user2 = new User("Luis Cunha", "luiscunha233", "1151581@isep.ipp.pt", "password", User.Language.PORTUGUESE);
        assertTrue(event.addOrganizer(new Organizer(user2)));
        int expected = 2;
        assertEquals(expected, event.getOrganizerRegister().getSize());

    }

    @Test
    public void testGetsDates() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        d1 = new Date(2017, 6, 9);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        assertEquals(event.getStartDate(), d1);
        assertEquals(event.getEndDate(), d2);
        assertEquals(event.getSubmissionStartDate(), d3);
        assertEquals(event.getSubmissionEndDate(), d4);

        d1 = new Date(2016, 5, 9);
        d2 = new Date(2016, 5, 10);
        d3 = new Date(2015, 12, 1);
        d4 = new Date(2016, 5, 1);

        event.setStartDate(d1);
        event.setEndDate(d2);
        event.setSubmissionStartDate(d3);
        event.setSubmissionEndDate(d4);

        assertEquals(event.getStartDate(), d1);
        assertEquals(event.getEndDate(), d2);
        assertEquals(event.getSubmissionStartDate(), d3);
        assertEquals(event.getSubmissionEndDate(), d4);

    }


    @Test
    public void testAddStand() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        d1 = new Date(2017, 6, 9);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        assertEquals(event.getStartDate(), d1);
        assertEquals(event.getEndDate(), d2);
        assertEquals(event.getSubmissionStartDate(), d3);
        assertEquals(event.getSubmissionEndDate(), d4);

        d1 = new Date(2016, 5, 9);
        d2 = new Date(2016, 5, 10);
        d3 = new Date(2015, 12, 1);
        d4 = new Date(2016, 5, 1);

        event.setStartDate(d1);
        event.setEndDate(d2);
        event.setSubmissionStartDate(d3);
        event.setSubmissionEndDate(d4);

        assertEquals(event.getStartDate(), d1);
        assertEquals(event.getEndDate(), d2);
        assertEquals(event.getSubmissionStartDate(), d3);
        assertEquals(event.getSubmissionEndDate(), d4);

    }

    @Test
    public void testGetStandList() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);

        StandList stand = new StandList();

        assertEquals(event.getStandRegister(), stand);
    }

    @Test
    public void TestEqualsDifferentDescriptions() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress("event", "description1", d1, d2, d3, d4, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    @Test
    public void TestEqualsDifferentStartDate() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress("event", "description2", d2, d2, d3, d4, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    @Test
    public void TestEqualsDifferentEndDate() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress("event", "description2", d1, d1, d3, d4, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    @Test
    public void TestEqualsDifferentSubmissionDate() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress("event", "description2", d1, d2, d4, d4, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    @Test
    public void TestEqualsDifferentEndSubmissionDate() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        Congress event2 = new Congress("event", "description2", d1, d2, d3, d3, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    @Test
    public void TestEqualsDifferentOrganizorsList() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event", "description2", d1, d2, d3, d4, user);
        user.add(new User("user1", "userName1", "user1@email.com", "password1", User.Language.ENGLISH));
        Congress event2 = new Congress("event", "description2", d1, d2, d3, d4, user);
        StandList stand = new StandList();

        assertNotEquals(event, event2);
    }

    /**
     * Test of getApplicationList method, of class Event.
     */
    @Test
    public void testGetApplicationList() {
        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);

        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Congress event = new Congress("event2", "description2", d1, d2, d3, d4, user);

        ApplicationList expected = new ApplicationList();

        ApplicationList result = event.getApplicationList();
        assertEquals(expected, result);

    }
}
