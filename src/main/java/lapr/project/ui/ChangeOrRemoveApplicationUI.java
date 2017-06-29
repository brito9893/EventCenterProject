package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

/**
 * Created by vitor on 07/06/2017.
 */
public class ChangeOrRemoveApplicationUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5434417168603381224L;

    /**
     * Change or remove application controller
     */
    private ChangeOrRemoveApplicationController controller;

    /**
     * Delete application button
     */
    private JButton deleteApplicationButton;

    /**
     * Change application button
     */
    private JButton changeApplicationButton;

    /**
     * Cancel button
     */
    private JButton cancelButton;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Applications JList
     */
    private JList<String> applicationsJL;

    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Applications Model
     */
    private DefaultListModel<String> applicationsModel;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Create create application Panel UI
     */
    private CreateCreateApplicationPanelUI createApplicationPanelUI;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    public ChangeOrRemoveApplicationUI(EventCenter eventCenter, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new ChangeOrRemoveApplicationController(eventCenter);

        eventsModel = new DefaultListModel<>();
        applicationsModel = new DefaultListModel<>();

        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (eventsJL.getSelectedIndex() != -1 && !"No events to show".equals(eventID)) {
                controller.getEventsAndApplicationsController().setEvent(eventID);
                updateApplicationModel();
                cancelButton.setEnabled(true);
            }
        });
        applicationsJL = new JList<>(applicationsModel);

        createComponents();
    }

    /**
     * Create frame components.
     */
    private void createComponents() {
        updateEventModel();
        add(new SelectEventPanelUI(eventsJL), BorderLayout.NORTH);
        add(createSelectApplicationPanel(), BorderLayout.CENTER);
        createChangeApplicationPanel();
        add(createApplicationPanelUI, BorderLayout.SOUTH);
    }

    /**
     * Update events model.
     */
    private void updateEventModel() {
        eventsModel.removeAllElements();
        applicationsModel.removeAllElements();
        List<String> eventList = controller.getEventsAndApplicationsController().getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
    }

    /**
     * Update applications model.
     */
    private void updateApplicationModel() {
        applicationsJL.clearSelection();
        applicationsModel.removeAllElements();
        createApplicationPanelUI.setButtons(false);
        createApplicationPanelUI.setChangeButtons(false);

        deleteApplicationButton.setEnabled(false);
        changeApplicationButton.setEnabled(false);
        String eventID = eventsJL.getSelectedValue();
        if (eventID != null && !"No events to show".equals(eventID)) {
            List<String> applicationsList = controller.getEventsAndApplicationsController().getApplications();
            for (String a : applicationsList) {
                applicationsModel.addElement(a);
            }
        }
    }

    /**
     * Create select application panel.
     *
     * @return select application panel.
     */
    private JPanel createSelectApplicationPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createApplicationsPanel(), BorderLayout.CENTER);
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
                String applicationID = applicationsJL.getSelectedValue();
                if (!("No applications to show".equals(applicationID))) {
                    controller.getEventsAndApplicationsController().setApplication(applicationID);
                    deleteApplicationButton.setEnabled(true);
                    changeApplicationButton.setEnabled(true);
                    createApplicationPanelUI.clearFields();
                    createApplicationPanelUI.setButtons(false);
                    createApplicationPanelUI.setChangeButtons(true);
                }
            }
        });
        JScrollPane scrPane = new JScrollPane(applicationsJL);

        applicationsPanel.add(scrPane, BorderLayout.CENTER);
        applicationsPanel.setBorder(new TitledBorder("Select application:"));

        applicationsPanel.add(createApplicationButtonsPanel(), BorderLayout.SOUTH);
        return applicationsPanel;
    }

    /**
     * Create application buttons Panel
     *
     * @return application buttons Panel
     */
    private JPanel createApplicationButtonsPanel() {
        createDeleteApplicationButton();
        createChangeApplicationButton();
        createCancelButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(deleteApplicationButton);
        p.add(changeApplicationButton);
        p.add(cancelButton);
        return p;
    }

    /**
     * Create delete application button.
     */
    private void createDeleteApplicationButton() {
        deleteApplicationButton = new JButton("Delete");
        deleteApplicationButton.addActionListener(e -> delete());
        deleteApplicationButton.setEnabled(false);
    }

    /**
     * Delete Dialog.
     */
    private void delete() {
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Delete application?", "Delete", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            controller.removeApplication();
            JOptionPane.showMessageDialog(mainWindow, "Application deleted with success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
            updateEventModel();
            mainWindow.updateTabs();
        }
    }

    /**
     * Create change application button.
     */
    private void createChangeApplicationButton() {
        changeApplicationButton = new JButton("Change");
        changeApplicationButton.addActionListener(e -> {
            createApplicationPanelUI.setButtons(true);
            eventsJL.setEnabled(false);
            applicationsJL.setEnabled(false);
            createApplicationPanelUI.setChangeButtons(false);
        });
        changeApplicationButton.setEnabled(false);
    }

    /**
     * Create change application panel.
     */
    private void createChangeApplicationPanel() {
        createApplicationPanelUI = new CreateCreateApplicationPanelUI(mainWindow, "Change Application", controller, "ChangeOrRemoveApplicationController", eventsJL);
        createApplicationPanelUI.setChangeApplicationObjects(changeApplicationButton, deleteApplicationButton, eventsModel, applicationsModel, applicationsJL, cancelButton);
    }

    /**
     * Create cancel button.
     */
    private void createCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            clear();
            eventsJL.setEnabled(true);
            applicationsJL.setEnabled(true);
        });
        cancelButton.setEnabled(false);
    }

    /**
     * Clear Fields and Selections
     */
    private void clear() {
        createApplicationPanelUI.clearFields();
        eventsJL.clearSelection();
        applicationsJL.clearSelection();
        createApplicationPanelUI.setButtons(false);
        createApplicationPanelUI.setChangeButtons(false);
        deleteApplicationButton.setEnabled(false);
        changeApplicationButton.setEnabled(false);
        cancelButton.setEnabled(false);
        updateEventModel();
    }
}
