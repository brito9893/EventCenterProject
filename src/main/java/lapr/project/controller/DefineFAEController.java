package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * Define FAE Controller
 */
public class DefineFAEController {

    /**
     * Event Center object
     */
    private final EventCenter eventCenter;
    /**
     * FAE object
     */
    private FAE fae;
    /**
     * FAE registry
     */
    private FAEList faeList;
    /**
     * Event Registry
     */
    private EventRegister eventRegister;
    /**
     * User Registry
     */
    private UserRegister userRegister;
    /**
     * Event
     */
    private User user;
    /**
     * User
     */
    private Event event;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    public DefineFAEController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * @return List of events
     */
    public List<String> getEvents() {
        eventRegister = eventCenter.getEventRegister();
        return eventRegister.getEventsNameList();
    }

    /**
     * @return List of users
     */
    public List<String> getUsers() {
        userRegister = eventCenter.getUserRegister();
        return userRegister.getUsersNameList();
    }

    /**
     * Set user and event
     *
     * @param userID  : User ID
     * @param eventID : Event ID
     */
    public void setUserAndEvent(String userID, String eventID) {
        user = userRegister.getUserByName(userID);
        event = eventRegister.getEvent(eventID);
    }

    /**
     * Check if user is event organizer
     *
     * @return logic value of success of operation
     */
    public boolean userIsEventOrganizer() {
        List<Organizer> organizers = event.getOrganizerRegister().getOrganizersList();
        for (Organizer o : organizers) {
            if (o.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates an FAE based on a user and assigns to an event
     */
    public void createFAE() {
        faeList = event.getFaeList();
        fae = new FAE(user);
    }

    /**
     * Adds an FAE to the FAE registry
     */
    public void addFAE() {
        faeList.addFAE(fae);
    }
}
