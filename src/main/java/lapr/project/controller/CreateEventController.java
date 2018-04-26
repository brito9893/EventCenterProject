package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import lapr.project.model.registers.EventRegister;
import lapr.project.model.registers.UserRegister;
import lapr.project.utils.Date.Date;

/**
 * Create Event Controller
 */
public class CreateEventController {

    /**
     * Event Center object
     */
    private final EventCenter eventCenter;
    /**
     * User registry
     */
    private UserRegister userRegister;
    /**
     * List of users
     */
    private List<User> users;
    /**
     * Name of the event
     */
    private String name;
    /**
     * Event type
     */
    private String type;
    /**
     * Event description
     */
    private String description;
    /**
     * Event start date
     */
    private Date eventStart;
    /**
     * Event finish date
     */
    private Date eventEnd;
    /**
     * Event Submission start date
     */
    private Date submissionStart;
    /**
     * Event submission end date
     */
    private Date submissionEnd;

    /**
     * Constructor for the controller
     *
     * @param eventCenter : Event Center
     */
    public CreateEventController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * Transforms a date in string into a date object
     *
     * @param dateString : Date in string
     * @return : Date object
     */
    private static Date stringToDate(String dateString) {
        String[] dateSplit = dateString.split("/");
        return new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt
                (dateSplit[2]));
    }

    /**
     * @return User list
     */
    public List<String> getUsers() {
        userRegister = eventCenter.getUserRegister();
        return userRegister.getUsersNameList();
    }

    /**
     * Sets the list of users
     *
     * @param userIDS : User list
     */
    public void setUserList(List<String> userIDS) {
        users = new ArrayList<>();
        for (String uid : userIDS) {
            users.add(userRegister.getUserByName(uid));
        }
    }

    /**
     * Sets the values for the event
     *
     * @param name                  : Event name
     * @param type                  : Event type
     * @param description           : Description
     * @param eventStartString      : Start Date
     * @param eventEndString        : End Date
     * @param submissionStartString : Submission Start Date
     * @param submissionEndString   : Submission End Date
     * @return if the information was set
     */
    public boolean setEvent(String name, String type, String description, String eventStartString, String eventEndString, String submissionStartString, String submissionEndString) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventStart = stringToDate(eventStartString);
        this.eventEnd = stringToDate(eventEndString);
        this.submissionStart = stringToDate(submissionStartString);
        this.submissionEnd = stringToDate(submissionEndString);
        return true;
    }

    /**
     * Creates an event object
     */
    public void createEvent() {
        EventRegister eventRegister = eventCenter.getEventRegister();
        Event event;
        if ("Exhibition".equals(type)) {
            event = new Exhibition();
        } else {
            event = new Congress();
        }

        event.setEventName(name);
        event.setDescription(description);
        event.setStartDate(eventStart);
        event.setEndDate(eventEnd);
        event.setSubmissionStartDate(submissionStart);
        event.setSubmissionEndDate(submissionEnd);
        event.addOrganizerList(users);

        eventRegister.addEvent(event);
    }
}
