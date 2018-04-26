package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.EventCenter;
import lapr.project.model.registers.EventRegister;

/**
 * List Applications Controller
 */
public class ListApplicationsController {

    /**
     * Event Center object
     */
    private EventCenter eventCenter;
    /**
     * Event registry
     */
    private EventRegister eventRegister;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    public ListApplicationsController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * @return Possible event states-
     */
    public String[] getStates() {
        return Event.getEventStates();
    }

    /**
     * Returns events on state passe as input
     *
     * @param state : Desired event state
     * @return : Event list
     */
    public List<String> getEvents(String state) {
        eventRegister = eventCenter.getEventRegister();
        List<Event> eventListByState = eventRegister.getEventListByState(state);
        List<String> eventNameList = new ArrayList<>();

        if (eventListByState.isEmpty()) {
            eventNameList.add("No events to show");
        } else {
            for (Event e : eventListByState) {
                eventNameList.add(e.getEventName());
            }
        }

        return eventNameList;
    }

    /**
     * @param eventID : Event identification
     * @return Applications
     */
    public List<String> getApplications(String eventID) {
        return eventRegister.getEvent(eventID).getApplicationList().getApplicationNameList();
    }
}
