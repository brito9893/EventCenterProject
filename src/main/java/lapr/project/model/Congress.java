/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.utils.Date.Date;

/**
 * @author U
 */
@XmlRootElement
public class Congress extends Event {
    /**
     * Event class constructor with parameters
     *
     * @param eventName           : Event name
     * @param eventDescription    : Event description
     * @param eventStartDate      : Event start date
     * @param eventEndDate        : Event end date
     * @param submissionStartDate : Submission period start date
     * @param submissionEndDate   : Submission period end date
     * @param orgList             : List of organizers
     */
    public Congress(String eventName, String eventDescription, Date eventStartDate, Date eventEndDate, Date submissionStartDate, Date submissionEndDate, List<User> orgList) {
        super(eventName, eventDescription, eventStartDate, eventEndDate, submissionStartDate, submissionEndDate, orgList);
    }

    /**
     * Congress class constructor through event parameter
     *
     * @param e : Event
     */
    public Congress(Event e) {
        super(e);
    }

    /**
     * Empty constructor for congress class
     */
    public Congress() {
        super();
    }
}
