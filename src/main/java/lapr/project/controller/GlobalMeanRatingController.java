package lapr.project.controller;

import lapr.project.model.EventCenter;

/**
 * Global mean rating controller
 */
public class GlobalMeanRatingController {

    /**
     * Event center object
     */
    private EventCenter ec;

    /**
     * Constructor for the controller
     *
     * @param ec : Event Center
     */
    public GlobalMeanRatingController(EventCenter ec) {
        this.ec = ec;
    }

    /**
     * @return global mean rating
     */
    public double calcGlobalMeanRating() {
        return ec.getEventRegister().calcGlobalMeanRating();
    }
}
