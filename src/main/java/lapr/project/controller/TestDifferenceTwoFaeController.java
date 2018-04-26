package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.model.Decision;
import lapr.project.model.Event;
import lapr.project.model.EventCenter;
import lapr.project.model.FAE;
import lapr.project.utils.Statistics;

/**
 * Created by Daniela Alves on 19/06/2017.
 */
public class TestDifferenceTwoFaeController {

    public static final double LEVEL_ONE = 2.575;
    public static final double LEVEL_TWO = 1.96;
    /**
     * Event Center object
     */
    private EventCenter ec;
    /**
     * Count A
     */
    private int countA = 30;
    /**
     * Count B
     */
    private int countB = 30;
    /**
     * FAE list
     */
    private List<FAE> faesList;
    /**
     * FAE A
     */
    private FAE faeA;
    /**
     * FAE B
     */
    private FAE faeB;
    /**
     * Mean Deviation A
     */
    private double deviationMeanFaeA;
    /**
     * Mean Deviation B
     */
    private double deviationMeanFaeB;
    /**
     * Variance on Mean Deviation A
     */
    private double varianceDeviationFaeA;
    /**
     * Variance on Mean Deviation B
     */
    private double varianceDeviationFaeB;

    /**
     * Constructor
     *
     * @param ec : Event Center
     */
    public TestDifferenceTwoFaeController(EventCenter ec) {
        this.ec = ec;
        deviationMeanFaeA = 0;
        deviationMeanFaeB = 0;
        varianceDeviationFaeA = 0;
        varianceDeviationFaeB = 0;
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
        } else {
            //to clear duplications
            Set<String> temp = new HashSet<>();
            temp.addAll(faeNameList);
            faeNameList.clear();
            faeNameList.addAll(temp);
        }
        return faeNameList;
    }

    /**
     * Defines FAEs to be compared
     *
     * @param faeIString : FAE 1
     * @param faeKString : FAE 2
     */
    public void setFAEs(String faeIString, String faeKString) {
        for (FAE f : faesList) {
            if (f.getUser().getName().equals(faeIString)) {
                faeA = f;
            }
            if (f.getUser().getName().equals(faeKString)) {
                faeB = f;
            }
        }
    }

    /**
     * Tests the difference between two FAE
     *
     * @return : difference
     */
    public double testDifferenceTwoFae() {
        double globalMeanFaeA = ec.getEventRegister().calculateFAEMeanRating(faeA);
        double globalMeanFaeB = ec.getEventRegister().calculateFAEMeanRating(faeB);

        List<Event> faeAEvents = new ArrayList<>();
        List<Event> faeBEvents = new ArrayList<>();

        for (Event e : ec.getEventRegister().getEventList()) {
            if (e.getFaeList().isFae(faeA)) {
                faeAEvents.add(e);
            }
            if (e.getFaeList().isFae(faeB)) {
                faeBEvents.add(e);
            }
        }

        List<Decision> decisionFaeA = new ArrayList<>();
        List<Decision> decisionFaeB = new ArrayList<>();

        for (Event e : faeAEvents) {
            decisionFaeA.addAll(e.getApplicationList().getFaeDecisions(faeA));
        }
        for (Event e : faeBEvents) {
            decisionFaeB.addAll(e.getApplicationList().getFaeDecisions(faeB));
        }

        if (decisionFaeA.isEmpty() || decisionFaeB.isEmpty()) {
            return 0;
        }

        List<Decision> randomListFaeA = Statistics.getRandom(decisionFaeA, countA);
        List<Decision> randomListFaeB = Statistics.getRandom(decisionFaeB, countB);

        List<Double> desviosFaeA = new ArrayList<>();
        List<Double> desviosFaeB = new ArrayList<>();

        for (Decision d : randomListFaeA) {
            desviosFaeA.add(Math.abs(d.calcAverageEvaluation() - globalMeanFaeA));
        }

        for (Decision d : randomListFaeB) {
            desviosFaeB.add(Math.abs(d.calcAverageEvaluation() - globalMeanFaeB));
        }

        double sum = 0;

        for (Double desv : desviosFaeA) {
            sum += desv;
        }

        deviationMeanFaeA = sum / desviosFaeA.size();

        sum = 0;

        for (Double desv : desviosFaeB) {
            sum += desv;
        }
        deviationMeanFaeB = sum / desviosFaeB.size();

        sum = 0;
        for (Double desv : desviosFaeA) {
            sum += Math.pow(desv - deviationMeanFaeA, 2);
        }

        varianceDeviationFaeA = sum / (desviosFaeA.size() - 1);

        sum = 0;
        for (Double desv : desviosFaeB) {
            sum += Math.pow(desv - deviationMeanFaeA, 2);
        }

        varianceDeviationFaeB = sum / (desviosFaeB.size() - 1);

        double zSuperior = deviationMeanFaeA - deviationMeanFaeB;

        double varianceI = varianceDeviationFaeA / countA;
        double varianceK = varianceDeviationFaeB / countB;

        return zSuperior / Math.sqrt((varianceI + varianceK));
    }

    public boolean checkIfThereIsDifference(double z, double zc) {
        return (z < -zc || z > zc);
    }

    /**
     * @return Count A
     */
    public int getCountA() {
        return countA;
    }

    /**
     * @return Count B
     */
    public int getCountB() {
        return countB;
    }

    /**
     * @return Deviation 1
     */
    public double getDeviationsI() {
        return deviationMeanFaeA;
    }

    /**
     * @return Deviation 2
     */
    public double getDeviationsK() {
        return deviationMeanFaeB;
    }
}
