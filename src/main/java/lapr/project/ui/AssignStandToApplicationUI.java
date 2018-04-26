package lapr.project.ui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import lapr.project.controller.AssignStandToApplicationController;
import lapr.project.model.EventCenter;

/**
 * Created by vitor on 10/06/2017.
 */
public class AssignStandToApplicationUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3027582104339773436L;

    /**
     * Assign stand to application controller
     */
    private AssignStandToApplicationController controller;

    /**
     * Applications JList
     */
    private JList<String> applicationsJL;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Stands JList
     */
    private JList<String> standsJL;

    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Applications Model
     */
    private DefaultListModel<String> applicationsModel;

    /**
     * Stands Model
     */
    private DefaultListModel<String> standsModel;

    /**
     * Show details button
     */
    private JButton showDetailsButton;

    /**
     * Confirm button
     */
    private JButton confirmButton;

    /**
     * Cancel button
     */
    private JButton cancelButton;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Constructor for the Assign stand to application UI
     *
     * @param eventCenter : Event Center
     */
    AssignStandToApplicationUI(EventCenter eventCenter, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new AssignStandToApplicationController(eventCenter);

        setJLists();
        updateEventsModel();
        createComponents();
    }

    /**
     * Set events, applications and stands JLists
     */
    private void setJLists() {
        eventsModel = new DefaultListModel<>();
        applicationsModel = new DefaultListModel<>();
        standsModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (eventsJL.getSelectedIndex() != -1 && !"No events to show".equals(eventID)) {
                controller.getEventsAndApplicationsController().setEvent(eventID);
                showDetailsButton.setEnabled(false);
                confirmButton.setEnabled(false);
                updateApplicationsModel();
                updateStandsModel();
            }
        });
        applicationsJL = new JList<>(applicationsModel);
        standsJL = new JList<>(standsModel);
    }

    /**
     * Update events model.
     */
    private void updateEventsModel() {
        eventsModel.removeAllElements();
        java.util.List<String> eventsList = controller.getEventsAndApplicationsController().getEvents();
        for (String e : eventsList) {
            eventsModel.addElement(e);
        }
    }


    /**
     * Create frame components.
     */
    private void createComponents() {
        add(new SelectEventPanelUI(eventsJL), BorderLayout.NORTH);
        add(createSelectApplicationAndStandPanel(), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * Update applications model.
     */
    private void updateApplicationsModel() {
        applicationsJL.clearSelection();
        applicationsModel.removeAllElements();
        java.util.List<String> applicationsList = controller.getEventsAndApplicationsController().getApplicationsWithoutStands();
        for (String a : applicationsList) {
            applicationsModel.addElement(a);
        }
    }

    /**
     * Update stands model.
     */
    private void updateStandsModel() {
        standsJL.clearSelection();
        standsModel.removeAllElements();
        List<String> standsList = controller.getStands();
        for (String s : standsList) {
            standsModel.addElement(s);
        }
    }

    /**
     * Create select application and stand panel.
     *
     * @return select application and stand panel.
     */
    private JPanel createSelectApplicationAndStandPanel() {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.add(createSelectApplicationsPanel());
        p.add(createStandsPanel());
        return p;
    }

    /**
     * Create select applications Panel
     *
     * @return select applications Panel
     */
    private JPanel createSelectApplicationsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createApplicationsPanel(), BorderLayout.CENTER);
        return p;
    }

    /**
     * Create show details button Panel
     *
     * @return Show details button Panel
     */
    private JPanel createShowDetailsButtonPanel() {
        createShowDetailsButton();
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(showDetailsButton);
        return p;
    }

    /**
     * Create applications panel that contains the applicationsJL.
     *
     * @return applications panel.
     */
    private JPanel createApplicationsPanel() {
        JPanel applicationsPanel = new JPanel(new BorderLayout());
        applicationsJL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        applicationsJL.addListSelectionListener(e -> {
            if (eventsJL.getSelectedIndex() != -1 && applicationsJL.getSelectedIndex() != -1) {
                if (!"No applications to show".equals(applicationsJL.getSelectedValue())) {
                    if (standsJL.getSelectedIndex() != -1) {
                        confirmButton.setEnabled(true);
                    }
                    showDetailsButton.setEnabled(true);
                }
                cancelButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(applicationsJL);

        applicationsPanel.add(scrPane, BorderLayout.CENTER);
        applicationsPanel.setBorder(new TitledBorder("Select application:"));

        applicationsPanel.add(createShowDetailsButtonPanel(), BorderLayout.SOUTH);
        return applicationsPanel;
    }

    /**
     * Create stands panel that contains the eventsJL.
     *
     * @return stand panel.
     */
    private JPanel createStandsPanel() {
        JPanel standsPanel = new JPanel(new BorderLayout());
        standsJL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        standsJL.addListSelectionListener(e -> {
            if (eventsJL.getSelectedIndex() != -1 && standsJL.getSelectedIndex() != -1) {
                if (applicationsJL.getSelectedIndex() != -1 && !"No stands to show".equals(standsJL.getSelectedValue())) {
                    confirmButton.setEnabled(true);
                }
                cancelButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(standsJL);

        standsPanel.add(scrPane, BorderLayout.CENTER);
        standsPanel.setBorder(new TitledBorder("Select stand:"));

        return standsPanel;
    }

    /**
     * Create buttons panel.
     *
     * @return buttons panel.
     */
    private JPanel createButtonsPanel() {

        createConfirmButton();
        createCancelButton();
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(confirmButton);
        p.add(cancelButton);
        return p;
    }

    /**
     * Create show details button.
     */
    private void createShowDetailsButton() {
        showDetailsButton = new JButton("Show application details");
        showDetailsButton.addActionListener(e -> {
            controller.getEventsAndApplicationsController().setApplication(applicationsJL.getSelectedValue());
            JOptionPane.showMessageDialog(mainWindow, new CreateApplicationDetailsPanelUI(controller, "AssignStandToApplicationController"), "Application details", JOptionPane.INFORMATION_MESSAGE);
        });
        showDetailsButton.setEnabled(false);
    }

    /**
     * Create confirm button.
     */
    private void createConfirmButton() {
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            controller.assignStandToApplication(standsJL.getSelectedValue(), applicationsJL.getSelectedValue());
            JOptionPane.showMessageDialog(mainWindow, "Application successfully assigned to stand!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
            mainWindow.updateTabs();
        });
        confirmButton.setEnabled(false);
    }

    /**
     * Create cancel button.
     */
    private void createCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> clear());
        cancelButton.setEnabled(false);
    }

    /**
     * Clear selections
     */
    private void clear() {
        eventsJL.clearSelection();
        applicationsJL.clearSelection();
        standsJL.clearSelection();
        applicationsModel.removeAllElements();
        standsModel.removeAllElements();
        confirmButton.setEnabled(false);
        showDetailsButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }
}
