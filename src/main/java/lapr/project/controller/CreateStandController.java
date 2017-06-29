package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

/**
 * Created by vitor on 08/06/2017.
 */
public class CreateStandController {

    /**
     * Description of the stand-
     */
    private String description;
    /**
     * Get event and application controller
     */
    private GetEventsAndApplicationsController getEventsAndApplicationsController;
    /**
     * Stands area
     */
    private int area;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    public CreateStandController(EventCenter eventCenter) {
        getEventsAndApplicationsController = new GetEventsAndApplicationsController(eventCenter);
    }

    /**
     * @return Get event and application controller
     */
    public GetEventsAndApplicationsController getEventsAndApplicationsController() {
        return getEventsAndApplicationsController;
    }

    /**
     * Sets the information for the stand
     *
     * @param description : Description
     * @param area        : Area
     * @return : True if information is defined successfully
     */
    public boolean setStand(String description, int area) {
        this.description = description;
        this.area = area;
        return true;
    }

    /**
     * Creates a stand and adds to the stand list
     */
    public void createStand() {
        StandList standList = getEventsAndApplicationsController.getEvent().getStandRegister();
        Stand stand = standList.newStand(area, description);
        standList.addStand(stand);
    }
}
