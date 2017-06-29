package lapr.project.model.registers;

import lapr.project.model.*;
import lapr.project.utils.Date.Date;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Event registry Created by vitor on 03/06/2017.
 */
@XmlRootElement
public class EventRegister {

    /**
     * ArrayList that contains Events.
     */
    @XmlElementWrapper(name = "Events")
    @XmlElement(name = "Event")
    private ArrayList<Event> eventList;

    /**
     * EventRegister Class Constructor.
     */
    public EventRegister() {
        eventList = new ArrayList<>();
    }

    /**
     * Creates a new congress
     *
     * @param eventName           congress name
     * @param eventDescription    congress description
     * @param eventStartDate      congress start date
     * @param eventEndDate        congress end date
     * @param submissionStartDate congress submissions start date
     * @param submissionEndDate   congress submissions end date
     * @param orgList             congress organizer List
     * @return new congress
     */
    public Event newCongress(String eventName, String eventDescription, Date eventStartDate, Date eventEndDate, Date submissionStartDate, Date submissionEndDate, List<User> orgList) {
        return new Congress(eventName, eventDescription, eventStartDate, eventEndDate, submissionStartDate, submissionEndDate, orgList);
    }

    /**
     * Creates a new exhibition
     *
     * @param eventName           exhibition name
     * @param eventDescription    exhibition description
     * @param eventStartDate      exhibition start date
     * @param eventEndDate        exhibition end date
     * @param submissionStartDate exhibition submissions start date
     * @param submissionEndDate   exhibition submissions end date
     * @param orgList             exhibition organizer List
     * @return new exhibition
     */
    public Event newExhibition(String eventName, String eventDescription, Date eventStartDate, Date eventEndDate, Date submissionStartDate, Date submissionEndDate, List<User> orgList) {
        return new Exhibition(eventName, eventDescription, eventStartDate, eventEndDate, submissionStartDate, submissionEndDate, orgList);
    }

    /**
     * Get event List
     *
     * @return Event List
     */
    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Get event's name List
     *
     * @return event's name List
     */
    public List<String> getEventsNameList() {
        List<String> eventNameList = new ArrayList<>();

        if (eventList.isEmpty()) {
            eventNameList.add("No events to show");
        } else {
            for (Event e : eventList) {
                eventNameList.add(e.getEventName());
            }
        }

        return eventNameList;
    }

    /**
     * Returns event list by given state.
     *
     * @param state given state.
     * @return event list by given state.
     */
    public List<Event> getEventListByState(String state) {
        List<Event> eventListByState = new ArrayList<>();
        for (Event e : eventList) {
            if (Objects.equals(e.getState(), state)) {
                eventListByState.add(e);
            }
        }
        return eventListByState;
    }

    /**
     * returns event list with Applications by given FAE.
     *
     * @param fae given FAE
     * @return list with Applications by given FAE.
     */
    public List<Event> getFaesEventsWithApplications(FAE fae) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event ev : eventList) {
            if (ev.getAttributionList().getSize() > 0 && ev.getAttributionList().faeHasAttribution(fae)) {
                events.add(ev);
            }
        }
        return events;
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return eventList.size();
    }

    /**
     * Add a new event to list.
     *
     * @param event Event to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addEvent(Event event) {
        if (!eventList.contains(event)) {
            return eventList.add(event);
        }
        return false;
    }

    /**
     * Removes a Event from the list.
     *
     * @param event Event to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeEvent(Event event) {
        return eventList.remove(event);
    }

    public void updateEventsState(Date currentDate) {
        for (Event e : eventList) {

            if (e.getSubmissionStartDate().isBigger(currentDate) && e.getFaeList().getSize() > 0) {
                e.setState(Event.NOT_STARTED_SUBMISSIONS);
                return;
            }
            if (currentDate.isBigger(e.getSubmissionStartDate()) && e.getSubmissionEndDate().isBigger(currentDate)) {
                e.setState(Event.STARTED_SUBMISSIONS);
                return;
            }
            if (currentDate.isBigger(e.getSubmissionEndDate()) && e.getStartDate().isBigger(currentDate)) {
                e.setState(Event.SUBMISSIONS_ON_DECISION);
                return;
            }
            if (currentDate.isBigger(e.getStartDate())) {
                e.setState(Event.STARTED);
                return;
            }
        }
    }

    /**
     * Obtains List of Events.
     *
     * @return Returns List of Events.
     */
    public List<Event> getEventsRegister() {
        return eventList;
    }

    /**
     * Obtains Event by his name.
     *
     * @param eventID event's name.
     * @return event with the given name.
     */
    public Event getEvent(String eventID) {
        Event event = null;
        for (Event e : eventList) {
            if (Objects.equals(e.getEventName(), eventID)) {
                event = e;
            }
        }
        return event;
    }

    /**
     * This method compares the equality of the current object with the object
     * of same type.
     *
     * @param o Object to be compared.
     * @return Logic value of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (!(o instanceof EventRegister)) {
            return false;
        }

        EventRegister that = (EventRegister) o;

        return eventList.containsAll(that.eventList);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Event a : eventList) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }

    /**
     * Return the global acceptance rate
     *
     * @return global acceptance rate
     */
    public double calculateTheGlobalAcceptanceRate() {
        double sum = 0;
        double global = 0;
        for (Event e : eventList) {
            sum += e.calculateTheAcceptanceRate();
        }
        global = sum / (eventList.size());
        return global;
    }

    /**
     * Return the global mean rating
     *
     * @return global mean rating
     */
    public double calcGlobalMeanRating() {
        List<FAE> faes = new ArrayList<>();
        for (Event e : eventList) {
            FAEList faeList = e.getFaeList();
            for (FAE fae : faeList.getFAEsList()) {
                if (!faes.contains(fae)) {
                    faes.add(fae);
                }
            }
        }
        double counter = (double) faes.size();
        double sum = 0;
        for (FAE f : faes) {
            if (hasReviewedApplication(f)) {
                sum += calculateFAEMeanRating(f);
            } else {
                counter--;
            }

        }
        return (faes.isEmpty()) ? 0 : sum / counter;
    }

    /**
     * Check if FAE has application
     *
     * @param fae FAE
     * @return Logic value of the success of operation
     */
    public boolean hasReviewedApplication(FAE fae) {
        for (Event e : eventList) {
            List<Application> application = e.getApplicationList().getApplicationList();
            for (Application a : application) {
                List<Decision> decision = a.getDecisionList().getDecisionsList();
                for (Decision d : decision) {
                    if (d.getDecisionAuthor().equals(fae)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return FAE mean rating
     *
     * @param fae FAE
     * @return FAE mean rating
     */
    public double calculateFAEMeanRating(FAE fae) {
        int counter = 0;
        double average = 0;
        for (Event e : eventList) {
            List<Application> application = e.getApplicationList().getApplicationList();
            for (Application a : application) {
                List<Decision> decision = a.getDecisionList().getDecisionsList();
                for (Decision d : decision) {
                    if (d.getDecisionAuthor().equals(fae)) {
                        average += d.calcAverageEvaluation();
                        counter++;
                    }

                }
            }
        }
        return (counter == 0) ? 0 : average / counter;
    }
}
