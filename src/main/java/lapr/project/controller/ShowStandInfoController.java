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
 * @author Luis Cunha on 21/06/2017
 */
public class ShowStandInfoController {

    private static final int COLUMN = 4;
    /**
     * Event Center object
     */
    private EventCenter center;
    /**
     * Event Registry
     */
    private EventRegister registerEvent;
    /**
     * List of areas
     */
    private List<Integer> areas;
    /**
     * List of class marks
     */
    private List<Double> classMarks;
    /**
     * List of frequencies for each area
     */
    private List<Integer> freq;
    /**
     * Number of classes to classify stands
     */
    private int nClass;

    /**
     * Constructor
     *
     * @param center : Event Center
     */
    public ShowStandInfoController(EventCenter center) {
        this.center = center;
    }

    /**
     * Rounds a number
     *
     * @param d : number
     * @return rounded number
     */
    private static double round(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    /**
     * @return List of events
     */
    public List<String> getEvents() {
        registerEvent = center.getEventRegister();
        return registerEvent.getEventsNameList();
    }

    /**
     * Returns stands and due information
     *
     * @param eventString : Event identification
     * @return stands with information
     */
    public String[][] getInfoStands(String eventString) {
        Event event = registerEvent.getEvent(eventString);
        areas = event.getStandRegister().getStandAreas();
        if (areas.size() == 0) {
            return null;
        }
        int min = areas.get(0);
        int max = areas.get(0);
        for (Integer area : areas) {
            if (area > max) {
                max = area;
            } else if (min > area) {
                min = area;
            }
        }

        int range = max - min;
        nClass = (int) Math.floor(1 + 3.322 * (Math.log10(areas.size())));
        double classRange = (double) range / nClass;
        int arrayRows = areas.size();
        String[][] array = new String[arrayRows][COLUMN];

        double last = min;
        classMarks = new ArrayList<>();
        freq = new ArrayList<>();
        for (int a = 0; a < nClass; a++) {
            double topClass = last + classRange;
            classMarks.add((topClass + last) / 2.0);
            String group = "[" + round(last) + " , " + round(topClass) + "[";

            Integer counter = 0;

            for (Integer area : areas) {
                int standArea = area;
                if ((standArea >= last && standArea < topClass) || (a == (nClass - 1) && standArea == max)) {
                    counter++;
                }
            }

            Double relativeFrequency = Math.round((counter.doubleValue() / (double) areas.size()) * 1000.0) / 10.0;
            array[a][0] = group;
            array[a][1] = counter.toString();
            freq.add(counter);
            array[a][2] = relativeFrequency.toString();
            last = topClass;
        }

        return array;

    }

    /**
     * @return Mean stand area
     */
    public double calculateMeanStandArea() {
        double sum = 0;

        for (int a = 0; a < nClass; a++) {
            sum += classMarks.get(a) * freq.get(a);
        }
        return sum / (double) areas.size();
    }

    /**
     * @return Standard deviation
     */
    public double calculateStandardDeviation() {
        double mean = calculateMeanStandArea();
        double sum = 0;
        for (int a = 0; a < classMarks.size(); a++) {
            sum += Math.pow((classMarks.get(a) - mean), 2) * freq.get(a);
        }
        return Math.sqrt((sum / (double) areas.size()));
    }
}
