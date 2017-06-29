/*  To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Arrays;

import lapr.project.model.registers.*;
import lapr.project.utils.Date.Date;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Luís Cunha on 02/06/2017
 */
public class Event {

    /**
     * Not started String
     */
    public static final String NOT_STARTED = "Not started";
    /**
     * Started String
     */
    public static final String STARTED = "Started";
    /**
     * Not started submissions String
     */
    public static final String NOT_STARTED_SUBMISSIONS = "Not started submissions";
    /**
     * Started submissions String
     */
    public static final String STARTED_SUBMISSIONS = "Started submissions";
    /**
     * Ended submissions String
     */
    public static final String ENDED_SUBMISSIONS = "Ended submissions";
    /**
     * Deciding submissions String
     */
    public static final String SUBMISSIONS_ON_DECISION = "Deciding submissions";
    /**
     * Event name
     */
    @XmlElement
    private String name;
    /**
     * Event eventDescription
     */
    @XmlElement
    private String eventDescription;
    /**
     * Event start date
     */
    @XmlElement
    private Date eventStartDate;
    /**
     * Event end date
     */
    @XmlElement
    private Date eventEndDate;
    /**
     * Event application submission start date
     */
    @XmlElement
    private Date eventSubmissionStartDate;
    /**
     * Event application submission end date
     */
    @XmlElement
    private Date eventSubmissionEndDate;
    /**
     * List ao event support employees
     */
    @XmlElement
    private FAEList listFae;
    /**
     * List of the organizers for the event
     */
    @XmlElement
    private OrganizerRegister listOrganizer;
    /**
     * List of applications to the event
     */
    @XmlElement
    private ApplicationList listApplication;
    /**
     * List of attributions to the event
     */
    @XmlElement
    private AttributionList listAttribution;
    /**
     * List of stands of the event
     */
    @XmlElement
    private StandList standList;
    /**
     * State of the event: Started, Not Started, Started submissions, Ended submissions
     */
    @XmlElement
    private String eventState;

    /**
     * Event class constructor with parameters
     *
     * @param eventName                : Event name
     * @param eventDescription         : Event eventDescription
     * @param eventStartDate           : Event start date
     * @param eventEndDate             : Event end date
     * @param eventSubmissionStartDate : Submission period start date
     * @param eventSubmissionEndDate   : Submission period end date
     * @param orgList                  : List of organizers
     */
    public Event(String eventName, String eventDescription, Date eventStartDate, Date eventEndDate, Date eventSubmissionStartDate, Date eventSubmissionEndDate, List<User> orgList) {

        this.name = eventName;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventSubmissionStartDate = eventSubmissionStartDate;
        this.eventSubmissionEndDate = eventSubmissionEndDate;
        this.listFae = new FAEList();
        this.listApplication = new ApplicationList();
        this.listOrganizer = new OrganizerRegister();
        this.listAttribution = new AttributionList();
        this.standList = new StandList();
        eventState = NOT_STARTED;
        addOrganizerList(orgList);
    }

    /**
     * Event class empty constructor
     */
    public Event() {
        this.listFae = new FAEList();
        this.listApplication = new ApplicationList();
        this.listOrganizer = new OrganizerRegister();
        this.listAttribution = new AttributionList();
        this.standList = new StandList();
        eventState = NOT_STARTED;
    }

    /**
     * Event class constructor through event parameter
     *
     * @param e : Event
     */
    public Event(Event e) {
        this.name = e.name;
        this.eventDescription = e.eventDescription;
        this.eventStartDate = e.eventStartDate;
        this.eventEndDate = e.eventEndDate;
        this.eventSubmissionStartDate = e.eventSubmissionStartDate;
        this.eventSubmissionEndDate = e.eventSubmissionEndDate;
        this.listFae = e.listFae;
        this.listOrganizer = e.listOrganizer;
        this.listApplication = e.listApplication;
        this.listAttribution = e.listAttribution;
        this.standList = e.standList;
        this.eventState = e.eventState;
    }

    /**
     * Returns an array with the event eventStates
     *
     * @return String array with eventStates
     */
    public static String[] getEventStates() {
        return new String[]{NOT_STARTED, STARTED, NOT_STARTED_SUBMISSIONS, STARTED_SUBMISSIONS, ENDED_SUBMISSIONS, SUBMISSIONS_ON_DECISION};
    }

    /**
     * Returns the event current eventState
     *
     * @return : Current eventState
     */
    public String getState() {
        return eventState;
    }

    /**
     * Sets the event eventState
     *
     * @param eventState : Event eventState
     */
    public boolean setState(String eventState) {
        List<String> states = new ArrayList<>(Arrays.asList(Event.getEventStates()));
        if (states.contains(eventState)) {
            this.eventState = eventState;
            return true;
        }
        return false;
    }

    /**
     * Adds an organizer list to the event
     *
     * @param orgList : Organizer List
     */
    public void addOrganizerList(List<User> orgList) {
        for (User o : orgList) {
            this.listOrganizer.addOrganizer(new Organizer(o));
        }
    }

    /**
     * Adds an organizer to organizer list
     *
     * @param o organizer
     * @return Logic value of the success of ooperation
     */
    public boolean addOrganizer(Organizer o) {
        return listOrganizer.addOrganizer(o);
    }

    /**
     * Returns the event name
     *
     * @return Event's name
     */
    public String getEventName() {
        return name;
    }

    /**
     * Sets the event name for the one passed in parameter
     *
     * @param eventName Event's name
     */
    public void setEventName(String eventName) {
        this.name = eventName;
    }

    /**
     * Get event's description
     *
     * @return event's description
     */
    public String getDescription() {
        return eventDescription;
    }

    /**
     * Set event's description
     *
     * @param eventDescription Event's description
     */
    public void setDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Get event start date
     *
     * @return Event start date
     */
    public Date getStartDate() {
        return eventStartDate;
    }

    /**
     * Set event start date
     *
     * @param eventStartDate Event start date
     */
    public void setStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    /**
     * Get event end date
     *
     * @return Event end date
     */
    public Date getEndDate() {
        return eventEndDate;
    }

    /**
     * Set event end date
     *
     * @param eventEndDate Event end date
     */
    public void setEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    /**
     * Get event submissions start date
     *
     * @return Event submissions start date
     */
    public Date getSubmissionStartDate() {
        return eventSubmissionStartDate;
    }

    /**
     * Set event submissions start date
     *
     * @param eventSubmissionStartDate Event submissions start date
     */
    public void setSubmissionStartDate(Date eventSubmissionStartDate) {
        this.eventSubmissionStartDate = eventSubmissionStartDate;
    }

    /**
     * Get event submissions end date
     *
     * @return Event submissions end date
     */
    public Date getSubmissionEndDate() {
        return eventSubmissionEndDate;
    }

    /**
     * Set event submissions end date
     *
     * @param eventSubmissionEndDate Event submissions end date
     */
    public void setSubmissionEndDate(Date eventSubmissionEndDate) {
        this.eventSubmissionEndDate = eventSubmissionEndDate;
    }

    /**
     * Get organizers List
     *
     * @return Organizers List
     */
    public OrganizerRegister getOrganizerRegister() {
        return listOrganizer;
    }

    /**
     * Get applications List
     *
     * @return Applications List
     */
    public ApplicationList getApplicationList() {
        return this.listApplication;
    }

    /**
     * Get attributions List
     *
     * @return Attributions List
     */
    public AttributionList getAttributionList() {
        return listAttribution;
    }

    /**
     * Get FAEs List
     *
     * @return FAEs List
     */
    public FAEList getFaeList() {
        return listFae;
    }

    /**
     * Get stands list
     *
     * @return Stands list
     */
    public StandList getStandRegister() {
        return standList;
    }

    /**
     * Calculate the acceptance rate
     *
     * @return Acceptance rate
     */
    public double calculateTheAcceptanceRate() {
        int counter = listApplication.getNumberOfAcceptedApplications();
        int submittedApplications = listApplication.getSize();
        return (submittedApplications == 0) ? 0 : (double) counter / (double) submittedApplications;

    }

    /**
     * This method compares the equality of the current object with the object
     * of same type.
     *
     * @param o Object to be compared.
     * @return Logic value of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Event)) {
            return false;
        }

        Event event = (Event) o;

        if (!name.equals(event.name)) {
            return false;
        }
        if (!eventDescription.equals(event.eventDescription)) {
            return false;
        }
        if (!eventStartDate.equals(event.eventStartDate)) {
            return false;
        }
        if (!eventEndDate.equals(event.eventEndDate)) {
            return false;
        }
        if (!eventSubmissionStartDate.equals(event.eventSubmissionStartDate)) {
            return false;
        }
        if (!eventSubmissionEndDate.equals(event.eventSubmissionEndDate)) {
            return false;
        }
        if (!listOrganizer.equals(event.listOrganizer)) {
            return false;
        }
        return listFae.equals(event.listFae);
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + eventDescription.hashCode();
        result = 31 * result + listOrganizer.hashCode();
        result = 31 * result + listFae.hashCode();
        return result;
    }

}

