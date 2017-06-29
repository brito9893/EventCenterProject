package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

/**
 * List Applications User Interface
 */
class ListApplicationsUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4405476010030864868L;

    /**
     * List applications controller
     */
    private ListApplicationsController controller;

    /**
     * States String[]
     */
    private String[] states;

    /**
     * Clean button
     */
    private JButton cleanButton;

    /**
     * Combo box
     */
    private JComboBox<String> comboBox;

    /**
     * Applications JList
     */
    private JList<String> applicationsJL;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Applications Model
     */
    private DefaultListModel<String> applicationsModel;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    ListApplicationsUI(EventCenter eventCenter) {
        super();

        setLayout(new BorderLayout());

        controller = new ListApplicationsController(eventCenter);

        eventsModel = new DefaultListModel<>();
        applicationsModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> updateApplicationsModel());
        applicationsJL = new JList<>(applicationsModel);

        states = controller.getStates();

        createComponents();
    }

    /**
     * Create frame components.
     */
    private void createComponents() {
        add(createSelectStatePanel(), BorderLayout.NORTH);
        add(new SelectEventPanelUI(eventsJL), BorderLayout.CENTER);
        add(createApplicationsPanel(), BorderLayout.SOUTH);
    }

    /**
     * Create select state panel.
     *
     * @return select state panel.
     */
    private JPanel createSelectStatePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createStatesPanel(), BorderLayout.CENTER);
        return p;
    }

    /**
     * Create states panel that contains the statesJL.
     *
     * @return states panel.
     */
    private JPanel createStatesPanel() {
        JPanel statesPanel = new JPanel(new BorderLayout());
        comboBox = new JComboBox<>(states);
        comboBox.addActionListener(event -> {
            applicationsModel.removeAllElements();
            updateEventsModel();
            cleanButton.setEnabled(true);
        });
        statesPanel.add(comboBox);
        statesPanel.setBorder(new TitledBorder("Select state:"));
        return statesPanel;
    }

    /**
     * Update events model.
     */
    private void updateEventsModel() {
        eventsModel.removeAllElements();
        List<String> eventList = controller.getEvents(comboBox.getSelectedItem().toString());
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
    }

    /**
     * Update applications model.
     */
    private void updateApplicationsModel() {
        applicationsModel.removeAllElements();
        String eventID = eventsJL.getSelectedValue();
        if (eventID != null && !"No events to show".equals(eventID)) {
            List<String> applicationsList = controller.getApplications(eventID);
            for (String a : applicationsList) {
                applicationsModel.addElement(a);
            }
        }
    }

    /**
     * Create applications panel that contains the applicationsJL.
     *
     * @return applications panel.
     */
    private JPanel createApplicationsPanel() {
        JPanel applicationsPanel = new JPanel(new BorderLayout());
        applicationsPanel.setBorder(new TitledBorder("Event's Applications:"));

        JScrollPane scrPane = new JScrollPane(applicationsJL);
        applicationsPanel.add(scrPane, BorderLayout.CENTER);

        applicationsPanel.add(createCleanButtonPanel(), BorderLayout.SOUTH);
        return applicationsPanel;
    }

    /**
     * Create clean button panel.
     *
     * @return clean button panel.
     */
    private JPanel createCleanButtonPanel() {
        createCleanButton();
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(cleanButton);
        return p;
    }

    /**
     * Create clean button.
     */
    private void createCleanButton() {
        cleanButton = new JButton("Clean");
        cleanButton.addActionListener(e -> {
            eventsJL.clearSelection();
            comboBox.setSelectedIndex(0);
            applicationsModel.removeAllElements();
            eventsModel.removeAllElements();
            cleanButton.setEnabled(false);
        });
        cleanButton.setEnabled(false);
    }
}
