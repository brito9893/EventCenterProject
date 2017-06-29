package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Mário Vaz
 */
class DefineFAEUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8753453183414772126L;
    /**
     * DefineFAE [UC2] controller.
     */
    private DefineFAEController controller;

    /**
     * Define FAE button
     */
    private JButton defineFAEButton;

    /**
     * Clear button
     */
    private JButton clearButton;

    /**
     * Users JList
     */
    private JList<String> usersJL;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Users Model
     */
    private DefaultListModel<String> usersModel;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    DefineFAEUI(EventCenter eventCenter, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;
        controller = new DefineFAEController(eventCenter);

        eventsModel = new DefaultListModel<>();
        usersModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (eventID != null && !"No events to show".equals(eventID)) {
                if (usersJL.getSelectedIndex() != -1 && !"No users to show".equals(usersJL.getSelectedValue())) {
                    defineFAEButton.setEnabled(true);
                }
                clearButton.setEnabled(true);
            }

        });
        usersJL = new JList<>(usersModel);

        createComponents();
    }

    /**
     * Create frame components.
     */
    private void createComponents() {
        updateModels();
        add(new SelectEventPanelUI(eventsJL), BorderLayout.NORTH);
        add(createSelectUserPanel(), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * Update user and event models.
     */
    private void updateModels() {
        List<String> eventList = controller.getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }

        List<String> userList = controller.getUsers();
        for (String u : userList) {
            usersModel.addElement(u);
        }
    }

    /**
     * Create select users panel.
     *
     * @return select users panel.
     */
    private JPanel createSelectUserPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createUsersPanel(), BorderLayout.CENTER);
        return p;
    }

    /**
     * Create users panel that contain usersJL.
     *
     * @return users panel.
     */
    private JPanel createUsersPanel() {
        JPanel usersPanel = new JPanel(new BorderLayout());
        usersJL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersJL.addListSelectionListener(e -> {
            String userID = usersJL.getSelectedValue();
            if (userID != null && !("No users to show".equals(userID))) {
                if (eventsJL.getSelectedIndex() != -1 && !"No events to show".equals(eventsJL.getSelectedValue())) {
                    defineFAEButton.setEnabled(true);
                }
                clearButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(usersJL);

        usersPanel.add(scrPane, BorderLayout.CENTER);
        usersPanel.setBorder(new TitledBorder("Select user:"));

        return usersPanel;
    }

    /**
     * Create buttons panel.
     *
     * @return buttons panel.
     */
    private JPanel createButtonsPanel() {
        createDefineFAEButton();
        createClearButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(defineFAEButton);
        p.add(clearButton);

        return p;
    }

    /**
     * Create define FAE button.
     */
    private void createDefineFAEButton() {
        defineFAEButton = new JButton("Define FAE");
        defineFAEButton.addActionListener(e -> {
            String userID = usersJL.getSelectedValue();
            String eventID = eventsJL.getSelectedValue();
            controller.setUserAndEvent(userID, eventID);
            if (controller.userIsEventOrganizer()) {
                JOptionPane.showMessageDialog(mainWindow, "User " + userID + " is an organizer of " + eventID + " event!", "Define FAE", JOptionPane.ERROR_MESSAGE);
            } else {
                controller.createFAE();
                confirmation();
            }
        });
        defineFAEButton.setEnabled(false);
    }

    /**
     * Create cancel selection button.
     */
    private void createClearButton() {
        clearButton = new JButton("Clear selection");
        clearButton.addActionListener(e -> {
            usersJL.clearSelection();
            eventsJL.clearSelection();
            defineFAEButton.setEnabled(false);
            clearButton.setEnabled(false);
        });
        clearButton.setEnabled(false);
    }

    /**
     * Confirmation Dialog.
     */
    private void confirmation() {
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Confirm FAE assignment?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            controller.addFAE(); //Add FAE to FAEList in controller.
            eventsJL.clearSelection();
            usersJL.clearSelection();
            defineFAEButton.setEnabled(false);
            clearButton.setEnabled(false);
            mainWindow.updateMenu();
        }
    }
}
