package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;
import lapr.project.utils.*;

import java.util.*;

/**
 * Controller for Test The Difference Between Two Events Acceptance Rate Use Case
 */
public class TestTheDifferenceBetweenTwoEventsAcceptanceRateController {
    public static final int SAMPLE = 30;
    /**
     * Event Center object
     */
    private EventCenter eventCenter;
    /**
     * Event 1
     */
    private Event event1;
    /**
     * Event 2
     */
    private Event event2;
    /**
     * Event Registry
     */
    private EventRegister eventRegister;

    /**
     * Constructor for the Test The Difference Between Two Events Acceptance Rate controller
     *
     * @param eventCenter : Event Center object
     */
    public TestTheDifferenceBetweenTwoEventsAcceptanceRateController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * Gets the list of events
     *
     * @return List of events
     */
    public List<String> getEvents() {
        eventRegister = eventCenter.getEventRegister();
        return eventCenter.getEventRegister().getEventsNameList();
    }

    /**
     * Sets the events to be compared
     *
     * @param event1ID : Event 1 identification
     * @param event2ID : Event 2 identification
     */
    public void setEvents(String event1ID, String event2ID) {
        event1 = eventRegister.getEvent(event1ID);
        event2 = eventRegister.getEvent(event2ID);
    }

    /**
     * Calculates the difference between two events acceptance rate
     *
     * @return calculated difference
     */
    public double calculateTheDifferenceBetweenTheTwoEventsAcceptanceRate() {

        int submittedApplications1 = event1.getApplicationList().getSize();
        int submittedApplications2 = event2.getApplicationList().getSize();

        List<Application> sampleEvent1 = Statistics.getRandom(event1.getApplicationList().getApplicationList(), SAMPLE);
        List<Application> sampleEvent2 = Statistics.getRandom(event1.getApplicationList().getApplicationList(), SAMPLE);

        double counterEvent1 = 0;
        for (Application app : sampleEvent1) {
            if (app.getDecision()) {
                counterEvent1++;
            }
        }
        double counterEvent2 = 0;
        for (Application app : sampleEvent2) {
            if (app.getDecision()) {
                counterEvent2++;
            }
        }

        double p1 = counterEvent1 / (double) sampleEvent1.size();
        double p2 = counterEvent2 / (double) sampleEvent2.size();

        double difference = p1 - p2;
        double q1 = 1 - p1;
        double q2 = 1 - p2;

        return difference / (Math.sqrt(((p1 * q1) / submittedApplications1) + ((p2 * q2) / submittedApplications2)));
    }

    /**
     * if the hypothesis is rejected for the significance level 1%
     *
     * @param squareProportion
     * @return Logic value
     */
    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1(double squareProportion) {
        double zc = 2.575;
        return !(-1 * zc < squareProportion && squareProportion < zc);
    }

    /**
     * if the hypothesis is rejected for the significance level 5%
     *
     * @param squareProportion
     * @return Logic value
     */
    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5(double squareProportion) {
        double zc = 1.96;
        return !(-1 * zc < squareProportion && squareProportion < zc);
    }


}
