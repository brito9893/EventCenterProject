package lapr.project.controller;

import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.EventCenter;
import lapr.project.model.registers.EventRegister;

/**
 * @author Liliana Lopes
 */
public class ShowEventAcceptanceRateController {

    /**
     * Event object
     */
    private Event event;
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
    public ShowEventAcceptanceRateController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * @return Event List
     */
    public List<String> getEvents() {
        eventRegister = eventCenter.getEventRegister();
        return eventRegister.getEventsNameList();
    }

    /**
     * Sets the needed event to the event passed as parameter
     *
     * @param eventID : Event ID
     */
    public void setEvent(String eventID) {
        event = eventRegister.getEvent(eventID);
    }

    /**
     * Calculates the acceptance rate on the event
     *
     * @return acceptance rate
     */
    public double calculateTheAcceptanceRate() {
        return Math.round(event.calculateTheAcceptanceRate() * 1000.0) / 10.0;
    }
}
