package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * Get Events And Applications Controller
 */
public class GetEventsAndApplicationsController {

    /**
     * Event Center object
     */
    private EventCenter eventCenter;
    /**
     * Event registry
     */
    private EventRegister eventRegister;
    /**
     * Event object
     */
    private Event event;
    /**
     * Application registry
     */
    private ApplicationList applicationRegister;
    /**
     * Application object
     */
    private Application application;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    GetEventsAndApplicationsController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * @return List of Events
     */
    public List<String> getEvents() {
        eventRegister = eventCenter.getEventRegister();
        return eventRegister.getEventsNameList();
    }

    /**
     * @return List of Applications
     */
    public List<String> getApplications() {
        applicationRegister = event.getApplicationList();
        return applicationRegister.getApplicationNameList();
    }

    /**
     * @return List of Applications without stands
     */
    public List<String> getApplicationsWithoutStands() {
        List<String> applicationNameList = getApplications();
        if (!"No applications to show".equals(applicationNameList.get(0))) {
            for (String application : getApplications()) {
                if (applicationRegister.getApplication(application).getStand() != null) {
                    applicationNameList.remove(application);
                }
            }
        }
        if (applicationNameList.isEmpty()) {
            applicationNameList.add("No applications to show");
        }
        return applicationNameList;
    }

    /**
     * @return Event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event being used to the event relative to the id passed as parameter
     *
     * @param eventID : Event ID
     */
    public void setEvent(String eventID) {
        event = eventRegister.getEvent(eventID);
    }

    /**
     * @return : Returns the application being used
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application being used
     *
     * @param applicationID : Application ID
     */
    public void setApplication(String applicationID) {
        application = applicationRegister.getApplication(applicationID);
    }

    /**
     * @return Application Register
     */
    ApplicationList getApplicationRegister() {
        return applicationRegister;
    }

    /**
     * @return Application Company
     */
    public String getApplicationCompany() {
        return application.getCompany();
    }

    /**
     * @return Application Description
     */
    public String getApplicationDescription() {
        return application.getDescription();
    }

    /**
     * @return Application Keywords
     */
    public String getApplicationKeywords() {
        KeywordList keywordRegister = application.getKeywordList();
        List<Keyword> keywordList = keywordRegister.getKeywordsList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Keyword k : keywordList) {
            stringBuilder.append(k);
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    /**
     * @return Number of application invites
     */
    public int getApplicationInvites() {
        return application.getInvites();
    }

    /**
     * @return Desired stand area
     */
    public int getApplicationStandArea() {
        return application.getStandArea();
    }
}
