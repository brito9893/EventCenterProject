package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * @author Liliana Lopes
 */
public class ShowAFaeMeanRatingController {
    /**
     * EventCenter
     */
    private final EventCenter eventCenter;

    /**
     * Event
     */
    private Event event;

    /**
     * FAE
     */
    private FAE fae;

    /**
     * FAEList
     */
    private FAEList faeRegister;

    /**
     * EventRegister
     */
    private EventRegister eventRegister;

    /**
     * Show a Fae Mean Rating Controller
     *
     * @param eventCenter
     */

    public ShowAFaeMeanRatingController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
        eventRegister = eventCenter.getEventRegister();
    }


    /**
     * Returns a list of events
     *
     * @return eventNameList
     */
    public List<String> getEvents() {
        return eventRegister.getEventsNameList();
    }

    /**
     * Returns the selected event using the eventID
     *
     * @param eventID
     */
    public void setEvent(String eventID) {
        event = eventRegister.getEvent(eventID);
    }

    /**
     * Returns a list of FAE's
     *
     * @return faeNameList
     */
    public List<String> getFaes() {
        faeRegister = event.getFaeList();
        return faeRegister.getFAEsNameList();
    }

    /**
     * Returns the selected FAE using the userID and the eventID
     *
     * @param userID
     * @param eventID
     */
    public void setFAE(String userID, String eventID) {
        event = eventRegister.getEvent(eventID);
        User user = eventCenter.getUserRegister().getUserByName(userID);

        faeRegister = event.getFaeList();
        List<FAE> faes = faeRegister.getFAEsList();

        for (FAE f : faes) {
            if (f.getUser().equals(user)) {
                fae = f;
            }
        }
    }

    /**
     * Method that calculates a fae mean rating
     *
     * @return faeMeanRating
     */
    public double calculateAFaeMeanRating() {
        return eventCenter.getEventRegister().calculateFAEMeanRating(fae);
    }

}
