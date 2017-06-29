package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * Submit application controller
 */
public class SubmitApplicationController {

    /**
     * Event center object
     */
    private final EventCenter eventCenter;
    /**
     * Event registry
     */
    private EventRegister eventRegister;
    /**
     * Event being applied to
     */
    private Event event;
    /**
     * Keyword list
     */
    private KeywordList keywordList;
    /**
     * Representative submitting the application
     */
    private Representative companyRepresentative;
    /**
     * Company
     */
    private String company;
    /**
     * Description of the company
     */
    private String description;
    /**
     * Current user in session
     */
    private User currentUser;
    /**
     * Number of invites
     */
    private int invites;
    /**
     * Stand are required
     */
    private int area;

    /**
     * Controller constructor
     *
     * @param eventCenter : Event center object
     * @param currentUser : User in session
     */
    public SubmitApplicationController(EventCenter eventCenter, User currentUser) {
        this.eventCenter = eventCenter;
        this.currentUser = currentUser;
    }

    /**
     * @return Event list on the event center
     */
    public List<String> getEvents() {
        List<String> events = new ArrayList<>();
        eventRegister = eventCenter.getEventRegister();
        for (Event e : eventRegister.getEventList()) {
            if (e.getState() == Event.STARTED_SUBMISSIONS) {
                events.add(e.getEventName());
            }
        }
        return events;
    }

    /**
     * Sets the event being applied to
     *
     * @param eventID : Event identification
     */
    public void setEvent(String eventID) {
        event = eventRegister.getEvent(eventID);
    }

    /**
     * Sets the application information
     *
     * @param company     : Company applying
     * @param description : Description of the company
     * @param invites     : Number of invites
     * @param area        : Area needed
     * @param keywords    : Keywords
     * @return : if the application was set or not
     */
    public boolean setApplication(String company, String description, int invites, int area, String keywords) {
        keywordList = new KeywordList();

        String[] keywordListArray = keywords.split(";");

        for (String keyword : keywordListArray) {
            keywordList.addKeyword(new Keyword(keyword.trim()));
        }

        this.companyRepresentative = new Representative(currentUser);
        this.company = company;
        this.description = description;
        this.invites = invites;
        this.area = area;
        return keywordListArray.length < 2 || keywordListArray.length > 5;
    }

    /**
     * Creates an application
     *
     * @return : Application
     */
    public void createApplication() {
        ApplicationList applicationList = event.getApplicationList();
        Application application = applicationList.createApplication(companyRepresentative, company, description, invites, area, keywordList);

        applicationList.addApplication(application);
    }
}
