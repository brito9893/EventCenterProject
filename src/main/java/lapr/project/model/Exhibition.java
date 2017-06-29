/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Date.Date;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author U
 */
@XmlRootElement
public class Exhibition extends Event {

    /**
     * Exhibition class constructor through event parameter
     *
     * @param e : Event
     */
    public Exhibition(Event e) {
        super(e);
    }

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
    public Exhibition(String eventName, String eventDescription, Date eventStartDate, Date eventEndDate, Date submissionStartDate, Date submissionEndDate, List<User> orgList) {
        super(eventName, eventDescription, eventStartDate, eventEndDate, submissionStartDate, submissionEndDate, orgList);
    }

    /**
     * Empty constructor for exhibition class
     */
    public Exhibition() {
        super();
    }
}
