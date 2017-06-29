/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;
import lapr.project.utils.Date.Date;
import lapr.project.utils.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Import Event Controller
 */
public class ImportEventController {

    /**
     * Integer value for an Exhibition
     */
    public static final int EXHIBITION = 0;
    /**
     * Integer value for a Congress
     */
    public static final int CONGRESS = 1;
    /**
     * Logger for Expections
     */
    private static final Logger LOGGER = Logger.getLogger(ImportEventController.class.getName());
    /**
     * Event Center object
     */
    private EventCenter center;
    /**
     * List of organizers
     */
    private ArrayList<User> organizers;
    /**
     * User registry
     */
    private UserRegister userRegister;
    /**
     * XML file
     */
    private File xmlFile;


    /**
     * Constructor
     *
     * @param center : Event Center
     * @param path   : Path to the file being imported
     */
    public ImportEventController(EventCenter center, String path) {
        xmlFile = new File(path);
        this.center = center;
        userRegister = center.getUserRegister();
    }

    /**
     * Converts a date in string to object date
     *
     * @param dateString : String date
     * @return : Object date
     */
    private static Date stringToDate(String dateString) {
        String[] dateSplit = dateString.split("/");
        return new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
    }

    /**
     * @return User List
     */
    public List<String> getUsers() {
        List<User> users = center.getUserRegister().getUsersList();
        List<String> usersName = new ArrayList<>();
        users.forEach(u -> usersName.add(u.getName()));

        return usersName;

    }

    /**
     * Adds to the organizer list all users passed on the input list
     *
     * @param userIDS : User identifications
     */
    public void getUserList(List<String> userIDS) {
        organizers = new ArrayList<>();
        userIDS.forEach(u -> organizers.add(userRegister.getUserByName(u)));
    }

    /**
     * Imports an event
     *
     * @param op           : Type of event
     * @param startDate    : Start Date
     * @param endDate      : End Date
     * @param startSubDate : Submission Start Date
     * @param endSubDate   : Submission End Date
     * @return : returns if the event was imported
     */
    public boolean importEvent(int op, String startDate, String endDate, String startSubDate, String endSubDate) {
        try {
            XMLReader reader = new XMLReader(xmlFile);
            EventRegister eventR = center.getEventRegister();
            switch (op) {
                case 0:
                    Exhibition ex = (Exhibition) eventR.newExhibition(reader.getEventTitle(), "", stringToDate(startDate), stringToDate(endDate), stringToDate(startSubDate), stringToDate(endSubDate), organizers);
                    addInfoToEvent(ex, reader);
                    center.getEventRegister().addEvent(ex);
                    return true;
                case 1:
                    Congress cong = (Congress) eventR.newCongress(reader.getEventTitle(), "", stringToDate(startDate), stringToDate(endDate), stringToDate(startSubDate), stringToDate(endSubDate), organizers);
                    addInfoToEvent(cong, reader);
                    center.getEventRegister().addEvent(cong);
                    return true;
                default:
                    return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.toString());
            return false;
        }

    }

    /**
     * Sets the information for an event
     *
     * @param ex     : Event
     * @param reader : XML reader
     */
    private void addInfoToEvent(Event ex, XMLReader reader) {
        List<FAE> faes = reader.getFaeList();
        faes.forEach(fae -> {
            ex.getFaeList().addFAE(fae);
            center.getUserRegister().addUser(fae.getUser());
        });
        List<Stand> stands = reader.getStandList();
        if (!stands.isEmpty()) {

            stands.forEach(s -> {
                StandList list = ex.getStandRegister();
                Stand s2 = list.newStand(s.getArea(), s.getDescription());
                list.addStand(s2);
            });
        }

        List<Application> applications = reader.getApplicationsRegister();

        applications.forEach(app -> ex.getApplicationList().addApplication(app));


        for (Application appli : applications) {
            if (appli.getDecisionList().getSize() > 0) {
                ex.setState(Event.SUBMISSIONS_ON_DECISION);
            }
            for (Decision d : appli.getDecisionList().getDecisionsList()) {
                ex.getAttributionList().addAttribution(new Attribution(d.getDecisionAuthor(), appli));
            }
        }
    }

}
