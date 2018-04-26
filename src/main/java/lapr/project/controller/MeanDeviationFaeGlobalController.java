package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Decision;
import lapr.project.model.Event;
import lapr.project.model.EventCenter;
import lapr.project.model.FAE;
import lapr.project.model.registers.EventRegister;

/**
 * Created by Daniela Alves on 16/06/2017.
 */
public class MeanDeviationFaeGlobalController {

    /**
     * Number of columns
     */
    private static final int COLUMNS = 3;
    /**
     * Event center object
     */
    private EventCenter ec;
    /**
     * Event object
     */
    private Event event;

    /**
     * Constructor
     *
     * @param ec : Event Center
     */
    public MeanDeviationFaeGlobalController(EventCenter ec) {
        this.ec = ec;

    }

    /**
     * @return Event list
     */
    public List<String> getEvents() {
        EventRegister eventRegister = ec.getEventRegister();
        return eventRegister.getEventsNameList();
    }

    /**
     * Sets the event relative to the test
     *
     * @param eventID : Event ID
     */
    public void setEvent(String eventID) {
        event = ec.getEventRegister().getEvent(eventID);
    }

    /**
     * @return Global mean FAE deviation
     */
    public String[][] calcMeanDeviationFaeGlobal() {

        double globalMean = ec.getEventRegister().calcGlobalMeanRating();

        List<FAE> faes = event.getFaeList().getFAEsList();

        String[][] info = new String[faes.size()][COLUMNS];

        int pos = 0;
        for (FAE fae : faes) {
            List<Decision> decision = new ArrayList<>();
            decision.addAll(event.getApplicationList().getFaeDecisions(fae));

            if (!decision.isEmpty()) {
                List<Double> faesDeviations = new ArrayList<>();

                decision.forEach(d -> faesDeviations.add(Math.abs(d.calcAverageEvaluation() - globalMean)));

                double sum = 0;

                for (Double d : faesDeviations) {
                    sum += d;
                }

                double mean = sum / decision.size();

                sum = 0;

                for (Double d : faesDeviations) {
                    sum += Math.pow(d - mean, 2);
                }

                double standart = Math.sqrt(sum / faesDeviations.size());

                info[pos][0] = fae.getUser().getName();
                info[pos][1] = String.valueOf(mean);
                info[pos][2] = String.valueOf(standart);
                pos++;

            } else {

                info[pos][0] = fae.getUser().getName();
                info[pos][1] = "No Reviews";
                info[pos][2] = "";
                pos++;

            }
        }
        return info;
    }

}
