/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Decision;
import lapr.project.model.EventCenter;
import lapr.project.model.FAE;

/**
 * @author Luís Cunha on 08/06/2017
 */
public class DecideApplicationController {

    /**
     * Current FAE in session
     */
    private FAE currentUser;
    /**
     * Final decision on application
     */
    private Decision finalDecision;
    /**
     * Get event and application controller
     */
    private GetEventsAndApplicationsController getEventsAndApplicationsController;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     * @param currentUser : Current user in session
     */
    public DecideApplicationController(EventCenter eventCenter, FAE currentUser) {
        this.currentUser = currentUser;
        getEventsAndApplicationsController = new GetEventsAndApplicationsController(eventCenter);
    }

    /**
     * @return Get event and application controller
     */
    public GetEventsAndApplicationsController getEventsAndApplicationsController() {
        return getEventsAndApplicationsController;
    }

    /**
     * Sets the final decision
     *
     * @param rates         : Rates
     * @param decision      : Acceptance status
     * @param justification : Justification
     * @return : true if decision is registered successfully
     */
    public boolean setDecision(List<Integer> rates, boolean decision, String justification) {
        finalDecision = getEventsAndApplicationsController.getApplication().getDecisionList().newDecision(rates, decision, justification, currentUser);
        return true;
    }

    /**
     * Adds the decision to the decision list on the application
     */
    public void submitDecision() {
        getEventsAndApplicationsController.getApplication().getDecisionList().addDecision(finalDecision);
    }
}
