package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.*;

import java.util.*;

/**
 * Test the difference on mean deviation in FAE according to default
 */
public class TestDifferenceMeanDeviationFaeController {

    private static final int SAMPLE = 30;
    /**
     * Event center object
     */
    private EventCenter ec;
    /**
     * Event support employee
     */
    private FAE fae;
    /**
     * FAE list
     */
    private List<FAE> faesList;
    /**
     * FAE mean rating
     */
    private double faeMeanRating;
    private double deviationMean = 0;
    private double standardDeviation = 0;

    /**
     * Constructor
     *
     * @param ec : Event Center
     */
    public TestDifferenceMeanDeviationFaeController(EventCenter ec) {
        this.ec = ec;
    }

    /**
     * Sets the FAE for the test
     *
     * @param faeString : FAE identifier
     */
    public void setFAE(String faeString) {
        for (FAE f : faesList) {
            if (f.getUser().getName().equals(faeString)) {
                fae = f;
            }
        }
    }

    /**
     * @return FAE list
     */
    public List<String> getFAEs() {
        List<String> faeNameList = new ArrayList<>();
        for (Event event : ec.getEventRegister().getEventList()) {
            faesList = event.getFaeList().getFAEsList();
            if (!faesList.isEmpty()) {
                for (FAE f : faesList) {
                    faeNameList.add(f.getUser().getName());
                }
            }
        }

        if (faeNameList.isEmpty()) {
            faeNameList.add("No FAEs to show");
        }
        return faeNameList;
    }

    /**
     * Test the FAE mean deviation with the default
     *
     * @return difference
     */
    public double testDifferenceMeanDeviationFae() {
        double globalMean = ec.getEventRegister().calculateFAEMeanRating(fae);

        List<Event> faeIEvents = new ArrayList<>();

        for (Event e : ec.getEventRegister().getEventList()) {
            if (e.getFaeList().isFae(fae)) {
                faeIEvents.add(e);
            }
        }

        List<Decision> decision = new ArrayList<>();
        for (Event e : faeIEvents) {
            decision.addAll(e.getApplicationList().getFaeDecisions(fae));
        }

        List<Decision> randomList = Statistics.getRandom(decision, SAMPLE);

        faeMeanRating = ec.getEventRegister().calculateFAEMeanRating(fae);

        List<Double> deviation = new ArrayList<>();

        for (Decision d : randomList) {
            deviation.add(Math.abs(d.calcAverageEvaluation() - globalMean));
        }

        double sum = 0;

        for (Double d : deviation) {
            sum += d;
        }

        deviationMean = sum / deviation.size();

        sum = 0;
        for (Double desv : deviation) {
            sum += Math.pow(desv - deviationMean, 2);
        }

        standardDeviation = sum / (deviation.size() - 1);

        return (deviationMean - 1) / Math.sqrt(standardDeviation / SAMPLE);

    }

    /**
     * @return COUNT
     */
    public int getCount() {
        return SAMPLE;
    }

    /**
     * @return Fae mean rating
     */
    public double getFaeMeanRating() {
        return faeMeanRating;
    }

    /**
     * @return Mean deviation
     */
    public double getDeviationMean() {
        return deviationMean;
    }

    /**
     * @return Standard Deviation
     */
    public double getStandardDeviation() {
        return standardDeviation;
    }

    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1(double z) {
        double zc = 2.33;
        return z > zc;
    }

    public boolean ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5(double z) {
        double zc = 1.645;
        return z > zc;
    }

}
