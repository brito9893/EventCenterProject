package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Mário Vaz on 08-Jun-17.
 */
public class SubmitApplicationUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3785840745492012588L;

    /**
     * Submit application controller
     */
    private SubmitApplicationController controller;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Create create application panel UI
     */
    private CreateCreateApplicationPanelUI createApplicationPanelUI;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     * @param currentUser Current User logged.
     */
    public SubmitApplicationUI(EventCenter eventCenter, User currentUser, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new SubmitApplicationController(eventCenter, currentUser);

        eventsModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (!("No events to show".equals(eventID))) {
                controller.setEvent(eventID);
                createApplicationPanelUI.setButtons(true);
            }
        });

        createComponents();
    }

    /**
     * Create frame components.
     */
    private void createComponents() {
        updateEventsModel();
        add(new SelectEventPanelUI(eventsJL), BorderLayout.CENTER);
        createNewApplicationPanel();
        add(createApplicationPanelUI, BorderLayout.SOUTH);
    }

    /**
     * Update events model.
     */
    private void updateEventsModel() {
        List<String> eventList = controller.getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
    }

    /**
     * Create new application panel.
     */
    private void createNewApplicationPanel() {
        createApplicationPanelUI = new CreateCreateApplicationPanelUI(mainWindow, "New Application", controller, "SubmitApplicationController", eventsJL);
    }
}
