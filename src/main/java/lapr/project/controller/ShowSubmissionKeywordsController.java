/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * Show event submission keywords use case controller
 *
 * @author Luís Cunha on 12/06/2017
 */
public class ShowSubmissionKeywordsController {

    /**
     * Event Center object
     */
    private EventCenter center;

    /**
     * Event registry
     */
    private EventRegister events;

    /**
     * Constructor
     *
     * @param center : Event Center
     */
    public ShowSubmissionKeywordsController(EventCenter center) {
        this.center = center;
    }

    /**
     * @return List of events
     */
    public List<String> getEvents() {
        events = center.getEventRegister();
        List<Event> eventList = events.getEventsRegister();
        List<String> eventNameList = new ArrayList<>();
        for (Event e : eventList) {
            eventNameList.add(e.getEventName());
        }

        return eventNameList;
    }

    /**
     * Gets a list of keywords for an event
     *
     * @param evName : Event identification
     * @return Map with keywords
     */
    public Map<String, Double> getEventKeywords(String evName) {
        Event ev = events.getEvent(evName);
        ApplicationList applications = ev.getApplicationList();
        return applications.getKeywords();
    }

}
