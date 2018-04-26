/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.EventCenter;

/**
 * @author Liliana Lopes
 */
public class ShowGlobalAcceptanceRateController {
    /**
     * Event center object
     */
    private EventCenter center;

    /**
     * Constructor for the controller
     *
     * @param center : event center object
     */
    public ShowGlobalAcceptanceRateController(EventCenter center) {
        this.center = center;
    }

    /**
     * Calculates the global acceptance rate
     *
     * @return : acceptance rate
     */
    public double calculateTheGlobalAcceptanceRate() {
        double acceptance = center.getEventRegister().calculateTheGlobalAcceptanceRate();
        return Math.round(acceptance * 1000.0) / 10.0;
    }
}
