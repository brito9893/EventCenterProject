package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * @author Liliana Lopes
 */
public class TestIfEventAcceptanceRateIsOverFiftyPercentController {

    private static final double ZC_LEVEL1 = 2.33;
    private static final double ZC_LEVEL5 = 1.645;
    /**
     * Event object
     */
    private Event event;
    /**
     * Event center object
     */
    private EventCenter eventCenter;
    /**
     * Event registry
     */
    private EventRegister eventRegister;

    /**
     * Constructor for
     *
     * @param eventCenter Test If Event Acceptance Rate Is Over Fifty Percent Controller
     */
    public TestIfEventAcceptanceRateIsOverFiftyPercentController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * Returns list of events of the event center
     *
     * @return Event list
     */
    public List<String> getEvents() {
        eventRegister = eventCenter.getEventRegister();
        return eventRegister.getEventsNameList();
    }

    /**
     * Sets the event relative to the test
     *
     * @param eventID : Event ID
     */
    public void setEvent(String eventID) {
        event = eventRegister.getEvent(eventID);
    }

    /**
     * Calculates the proportion of accepted applications
     *
     * @return : proportion
     */
    private double calculateTheProportion() {
        int submittedApplications = event.getApplicationList().getSize();

        double p0 = 0.5;
        double p = event.calculateTheAcceptanceRate();

        double difference = p - p0;

        return difference / (Math.sqrt(((p0 * (1 - p0)) / submittedApplications)));
    }

    /**
     * ensures if the hypothesis is rejected for the significance level 1%
     *
     * @return Logic value
     */
    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1() {
        double squareProportion = calculateTheProportion();
        return squareProportion > ZC_LEVEL1;
    }

    /**
     * ensures if the hypothesis is rejected for the significance level 5%
     *
     * @return Logic value
     */
    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5() {
        double squareProportion = calculateTheProportion();
        return squareProportion > ZC_LEVEL5;
    }


}
