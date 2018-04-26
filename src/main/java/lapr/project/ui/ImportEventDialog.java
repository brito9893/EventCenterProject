/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import lapr.project.controller.ImportEventController;
import lapr.project.model.EventCenter;

/**
 * @author U
 */
public class ImportEventDialog extends JDialog {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 1L;
    /**
     * New Event String
     */
    private static final String NEW_EVENT = "New Event";
    /**
     * Number of users to select
     */
    private static final int USERS_TO_SELECT = 2;
    /**
     * Users JList
     */
    private JList<String> usersJL;
    /**
     * Users Model
     */
    private DefaultListModel<String> usersModel;
    /**
     * Users ID's List that contain both User ID's
     */
    private List<String> userIDS;
    /**
     * Select Button
     */
    private JButton selectButton;
    /**
     * Create Button
     */
    private JButton createButton;
    /**
     * Exhibition Radio Button
     */
    private JRadioButton exhibitionRadio;
    /**
     * Import event controller
     */
    private ImportEventController controller;
    /**
     * Start Date
     */
    private JDateChooser startDate;
    /**
     * End Date
     */
    private JDateChooser endDate;
    /**
     * Start submissions Date
     */
    private JDateChooser startSubDate;
    /**
     * End submissions Date
     */
    private JDateChooser startSubEndDate;

    /**
     * Class Constructor
     *
     * @param frame  JFrame
     * @param name   User name
     * @param model  Model boolean
     * @param center Event Center
     * @param file   File path
     */
    public ImportEventDialog(JFrame frame, String name, boolean model, EventCenter center, String file) {
        super(frame, name, model);
        controller = new ImportEventController(center, file);
        usersModel = new DefaultListModel<>();
        usersJL = new JList<>(usersModel);
        this.setLayout(new BorderLayout());
        add(createEventType(), BorderLayout.NORTH);
        add(createDatesPanel(), BorderLayout.CENTER);
        add(createSelectUsersPanel(), BorderLayout.SOUTH);
        this.setLocationRelativeTo(frame);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

    }

    /**
     * Create select users Panel
     *
     * @return select users Panel
     */
    private JPanel createSelectUsersPanel() {
        updateUsersModel();
        createCreateButton();
        JPanel p = new JPanel(new BorderLayout());
        p.add(createUsersPanel(), BorderLayout.NORTH);
        p.add(createOrganizerButtonsPanel(), BorderLayout.CENTER);
        p.add(createButton, BorderLayout.SOUTH);
        return p;
    }

    /**
     * Create organizer buttons panel
     *
     * @return organizer buttons panel
     */
    private JPanel createOrganizerButtonsPanel() {
        createSelectButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(selectButton);

        return p;
    }

    /**
     * Update users Model
     */
    private void updateUsersModel() {
        List<String> userList = controller.getUsers();
        for (String u : userList) {
            usersModel.addElement(u);
        }
    }

    /**
     * Create users panel that contains the usersJL.
     *
     * @return users panel.
     */
    private JPanel createUsersPanel() {
        JPanel usersPanel = new JPanel(new BorderLayout());

        usersJL.setSelectionModel(new DefaultListSelectionModel() {
            public static final long serialVersionUID = 2L;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        usersJL.addListSelectionListener(e -> {
            userIDS = usersJL.getSelectedValuesList();
            if (userIDS != null && usersJL.getSelectedIndex() != -1 && !"No users to show".equals(userIDS.get(0))) {
                selectButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(usersJL);

        usersPanel.add(scrPane, BorderLayout.CENTER);
        usersPanel.setBorder(new TitledBorder("Select organizers:"));

        return usersPanel;
    }

    /**
     * Create select organizers button
     */
    private void createSelectButton() {
        selectButton = new JButton("Select organizers");
        selectButton.addActionListener(e -> {
            userIDS = usersJL.getSelectedValuesList();
            if (userIDS.size() < USERS_TO_SELECT || usersJL.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(ImportEventDialog.this, "You must at least TWO (2) organizers", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
            } else {
                controller.getUserList(userIDS);
                createButton.setEnabled(true);
            }
        });
        selectButton.setMnemonic(KeyEvent.VK_S);
        selectButton.setEnabled(false);
    }

    /**
     * Create event type Panel
     *
     * @return event type Panel
     */
    private JPanel createEventType() {
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        exhibitionRadio = new JRadioButton("Exhbition");
        JRadioButton congressRadio = new JRadioButton("Congress");
        exhibitionRadio.setSelected(true);
        buttonGroup.add(exhibitionRadio);
        buttonGroup.add(congressRadio);
        panel.add(exhibitionRadio);
        panel.add(congressRadio);
        return panel;
    }

    /**
     * Create dates Panel
     *
     * @return dates Panel
     */
    private JPanel createDatesPanel() {
        startDate = new JDateChooser();
        endDate = new JDateChooser();
        startSubDate = new JDateChooser();
        startSubEndDate = new JDateChooser();

        startDate.setToolTipText("Start Date");
        endDate.setToolTipText("End Date");
        startSubDate.setToolTipText("Start Submission Date");
        startSubEndDate.setToolTipText("End Submission Date");

        JPanel panel = new JPanel(new GridLayout(2, 0));
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel startSubDatesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        startDatePanel.add(startDate);
        startDatePanel.add(endDate);
        startSubDatesPanel.add(startSubDate);
        startSubDatesPanel.add(startSubEndDate);
        startDatePanel.setBorder(new TitledBorder("Event Dates"));
        startSubDatesPanel.setBorder(new TitledBorder("Event Submission Dates"));
        panel.add(startDatePanel);
        panel.add(startSubDatesPanel);

        return panel;
    }

    /**
     * Create create Button
     */
    private void createCreateButton() {
        createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            int type;
            if (exhibitionRadio.isSelected()) {
                type = ImportEventController.EXHIBITION;
            } else {
                type = ImportEventController.CONGRESS;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String eventStartString = sdf.format(startDate.getDate().getTime());
            String eventEndString = sdf.format(endDate.getDate().getTime());
            String submissionStartString = sdf.format(startSubDate.getDate().getTime());
            String submissionEndString = sdf.format(startSubEndDate.getDate().getTime());

            if ("".equals(eventStartString) || "".equals(eventEndString) || "".equals(submissionStartString) || "".equals(submissionEndString)) {
                JOptionPane.showMessageDialog(ImportEventDialog.this, "Incomplete information", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
            } else if (endDate.equals(startDate) || startSubDate.equals(startSubEndDate) || startDate.getDate().after(endDate.getDate()) || startSubDate.getDate().after(startSubEndDate.getDate())) {
                JOptionPane.showMessageDialog(ImportEventDialog.this, "Invalid dates", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
            } else {

                boolean eventAdded = controller.importEvent(type, eventStartString, eventEndString, submissionStartString, submissionEndString);
                if (!eventAdded) {
                    JOptionPane.showMessageDialog(ImportEventDialog.this, "Event wasn't imported", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
                }
                dispose();

            }
        });
        createButton.setMnemonic(KeyEvent.VK_ENTER);
        createButton.setEnabled(false);
    }
}
