package lapr.project.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import lapr.project.controller.CreateEventController;
import lapr.project.model.EventCenter;

/**
 * Graphical user interface for Use Case 1
 * <p>
 * Created by vitor on 02/06/2017.
 */
public class CreateEventUI extends JPanel {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 2L;

    /**
     * Label Size Dimension
     */
    private static final Dimension LABEL_SIZE = new JLabel("Event's name:").getPreferredSize();

    /**
     * Text Field Width
     */
    private static final int TEXT_FIELD_WIDTH = 20;

    /**
     * Minimum nmber of users to select
     */
    private static final int MIN_USERS_TO_SELECT = 2;

    /**
     * New Event String
     */
    private static final String NEW_EVENT = "New Event";

    /**
     * Create event controller
     */
    private CreateEventController controller;

    /**
     * Users JList
     */
    private JList<String> usersJL;

    /**
     * Users Model
     */
    private DefaultListModel<String> usersModel;

    /**
     * Name TextField
     */
    private JTextField txtName;

    /**
     * Description TextArea
     */
    private JTextArea txtDescription;

    /**
     * Event start Date Chooser
     */
    private JDateChooser eventStart;

    /**
     * Event end Date Chooser
     */
    private JDateChooser eventEnd;

    /**
     * Submissions start Date Chooser
     */
    private JDateChooser submissionStart;

    /**
     * Submissions end Date Chooser
     */
    private JDateChooser submissionEnd;

    /**
     * Create button
     */
    private JButton createButton;

    /**
     * Clear button
     */
    private JButton clearButton;

    /**
     * Select button
     */
    private JButton selectButton;

    /**
     * Cancel button
     */
    private JButton cancelButton;

    /**
     * Exhibition JRadio button
     */
    private JRadioButton exhibition;

    /**
     * Congress JRadio button
     */
    private JRadioButton congress;

    /**
     * Button group
     */
    private ButtonGroup buttonGroup;

    /**
     * Users ID's List that contain both user ID's
     */
    private List<String> userIDS;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    CreateEventUI(EventCenter eventCenter, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new CreateEventController(eventCenter);

        usersModel = new DefaultListModel<>();
        usersJL = new JList<>(usersModel);

        createComponents();
    }

    /**
     * Check if something is filled
     *
     * @param name                  event name
     * @param type                  event type
     * @param description           event description
     * @param eventStartString      event start date string
     * @param eventEndString        event end date string
     * @param submissionStartString event submissions start date string
     * @param submissionEndString   event submissions end date string
     * @return Logic value of success of operation
     */
    private static boolean anythingLeft(String name, String type, String description, String eventStartString, String eventEndString, String submissionStartString, String submissionEndString) {
        return "".equals(name) || "".equals(type) || "".equals(description) || "".equals(eventStartString) || "".equals(eventEndString) || "".equals(submissionStartString) || "".equals(submissionEndString);
    }

    /**
     * Create frame components.
     */
    private void createComponents() {
        updateUsersModel();
        add(createSelectUsersPanel(), BorderLayout.NORTH);
        add(createNewEventPanel(), BorderLayout.CENTER);
    }

    /**
     * Update users model.
     */
    private void updateUsersModel() {

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
    private JPanel createSelectUsersPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createUsersPanel(), BorderLayout.CENTER);
        p.add(createOrganizerButtonsPanel(), BorderLayout.SOUTH);
        return p;
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
     * Create organizers buttons
     *
     * @return organizers buttons
     */
    private JPanel createOrganizerButtonsPanel() {
        createSelectButton();
        createCancelButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(selectButton);
        p.add(cancelButton);

        return p;
    }

    /**
     * Create select organizers button
     */
    private void createSelectButton() {
        selectButton = new JButton("Select organizers");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userIDS = usersJL.getSelectedValuesList();
                if (userIDS.size() < MIN_USERS_TO_SELECT || usersJL.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(mainWindow, "You must select at least TWO (2) organizers", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
                } else {
                    controller.setUserList(userIDS);
                    selectedUsers(true);
                }
            }
        });
        selectButton.setMnemonic(KeyEvent.VK_S);
        selectButton.setEnabled(false);
    }

    /**
     * Create cancel button
     */
    private void createCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            selectedUsers(false);
            clearFields();
            usersJL.clearSelection();
            selectButton.setEnabled(false);
        });
        cancelButton.setEnabled(false);
    }

    /**
     * Set new Event buttons and JTexts enabled/editable or not.
     *
     * @param value what defines if JTexts are enabled or not.
     */
    private void selectedUsers(boolean value) {
        selectButton.setEnabled(!value);
        usersJL.setEnabled(!value);
        cancelButton.setEnabled(value);
        txtDescription.setEditable(value);
        txtName.setEditable(value);
        exhibition.setEnabled(value);
        congress.setEnabled(value);
        eventEnd.setEnabled(value);
        eventStart.setEnabled(value);
        submissionEnd.setEnabled(value);
        submissionStart.setEnabled(value);
        somethingFilled();
    }

    /**
     * Create new event panel.
     *
     * @return new event panel.
     */
    private JPanel createNewEventPanel() {
        JPanel newEventPanel = new JPanel(new GridLayout(5, 1));
        newEventPanel.setBorder(new TitledBorder("New Event:"));

        newEventPanel.add(createNamePanel());
        newEventPanel.add(createTypePanel());
        newEventPanel.add(createDescriptionPanel());
        newEventPanel.add(createDatesPanel());
        newEventPanel.add(createEventButtonsPanel());

        return newEventPanel;
    }

    /**
     * Determines if any info is filled.
     */
    private void somethingFilled() {
        if (txtDescription.getText().length() == 0 && txtName.getText().length() == 0 && !exhibition.isSelected()) {
            if (!congress.isSelected() && eventEnd.getDate() == null && eventStart.getDate() == null) {
                if (submissionEnd.getDate() == null && submissionStart.getDate() == null) {
                    clearButton.setEnabled(false);
                    createButton.setEnabled(false);
                } else {
                    clearButton.setEnabled(true);
                    createButton.setEnabled(true);
                }
            } else {
                clearButton.setEnabled(true);
                createButton.setEnabled(true);
            }
        } else {
            clearButton.setEnabled(true);
            createButton.setEnabled(true);
        }
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set event's name.
     *
     * @return panel to set event's name
     */
    private JPanel createNamePanel() {
        JLabel lbl = new JLabel("Event's name:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        txtName = new JTextField(TEXT_FIELD_WIDTH);
        txtName.setEditable(false);
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                somethingFilled();
            }
        });

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtName);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set event type.
     *
     * @return panel to set event type
     */
    private JPanel createTypePanel() {
        JLabel lbl = new JLabel("Type:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        exhibition = new JRadioButton("Exhibition");
        exhibition.setEnabled(false);
        exhibition.addActionListener(e -> somethingFilled());

        congress = new JRadioButton("Congress");
        congress.setEnabled(false);
        congress.addActionListener(e -> somethingFilled());

        buttonGroup = new ButtonGroup();
        buttonGroup.add(exhibition);
        buttonGroup.add(congress);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(exhibition);
        p.add(congress);

        return p;
    }

    /**
     * Create event's description panel with textArea.
     *
     * @return event's description panel with textArea
     */
    private JPanel createDescriptionPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Description"));
        createTxtDescription();
        JScrollPane scrollPane = new JScrollPane(txtDescription);
        p.add(scrollPane, BorderLayout.CENTER);
        txtDescription.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                somethingFilled();
            }
        });
        return p;
    }

    /**
     * Create text Area to set event's description.
     */
    private void createTxtDescription() {
        txtDescription = new JTextArea(2, 1);
        txtDescription.setLineWrap(true);
        txtDescription.setFont(new Font(txtDescription.getFont().getName(), txtDescription.getFont().getStyle(), 14));
        txtDescription.setEditable(false);
    }

    /**
     * Create dates Panel
     *
     * @return dates Panel
     */
    private JPanel createDatesPanel() {
        JPanel p = new JPanel(new GridLayout(1, 2));
        p.setBorder(new TitledBorder("Dates"));

        p.add(createEventDatesPanel());
        p.add(createSubmissionDatesPanel());

        return p;
    }

    /**
     * Create event dates panel with JDateChooser.
     *
     * @return event dates panel with JDateChooser
     */
    private JPanel createEventDatesPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBorder(new TitledBorder("Event"));

        JLabel start = new JLabel("Start:", JLabel.RIGHT);
        start.setPreferredSize(LABEL_SIZE);
        p.add(start);

        eventStart = new JDateChooser();
        eventStart.setEnabled(false);

        p.add(eventStart);
        eventStart.getDateEditor().addPropertyChangeListener(e -> somethingFilled());

        JLabel end = new JLabel("End:", JLabel.RIGHT);
        end.setPreferredSize(LABEL_SIZE);
        p.add(end);

        eventEnd = new JDateChooser();
        eventEnd.setEnabled(false);

        eventEnd.getDateEditor().addPropertyChangeListener(e -> somethingFilled());

        p.add(eventEnd);
        return p;
    }

    /**
     * Create submission dates panel with JDateChooser.
     *
     * @return submission dates panel with JDateChooser
     */
    private JPanel createSubmissionDatesPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBorder(new TitledBorder("Submission"));

        JLabel start = new JLabel("Start:", JLabel.RIGHT);
        start.setPreferredSize(LABEL_SIZE);
        p.add(start);

        submissionStart = new JDateChooser();
        submissionStart.setEnabled(false);

        p.add(submissionStart);
        submissionStart.getDateEditor().addPropertyChangeListener(e -> somethingFilled());

        JLabel end = new JLabel("End:", JLabel.RIGHT);
        end.setPreferredSize(LABEL_SIZE);
        p.add(end);

        submissionEnd = new JDateChooser();
        submissionEnd.setEnabled(false);

        p.add(submissionEnd);
        submissionEnd.getDateEditor().addPropertyChangeListener(e -> somethingFilled());
        return p;
    }

    /**
     * Create event buttons panel.
     *
     * @return event buttons panel.
     */
    private JPanel createEventButtonsPanel() {
        createCreateButton();

        createClearButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(createButton);
        p.add(clearButton);

        return p;
    }

    /**
     * Create create button.
     */
    private void createCreateButton() {
        createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            String name = txtName.getText();
            String type;
            if (exhibition.isSelected()) {
                type = "Exhibition";
            } else {
                type = "Congress";
            }
            String description = txtDescription.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String eventStartString = sdf.format(eventStart.getDate().getTime());
            String eventEndString = sdf.format(eventEnd.getDate().getTime());
            String submissionStartString = sdf.format(submissionStart.getDate().getTime());
            String submissionEndString = sdf.format(submissionEnd.getDate().getTime());

            if (anythingLeft(name, type, description, eventStartString, eventEndString, submissionStartString, submissionEndString)) {
                JOptionPane.showMessageDialog(mainWindow, "Incomplete information", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
            } else if (eventEnd.equals(eventStart) || submissionEnd.equals(submissionStart) || eventStart.getDate().after(eventEnd.getDate()) || submissionStart.getDate().after(submissionEnd.getDate()) || submissionStart.getDate().after(eventStart.getDate())) {
                JOptionPane.showMessageDialog(mainWindow, "Invalid dates", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean eventAdded = controller.setEvent(name, type, description, eventStartString, eventEndString, submissionStartString, submissionEndString);
                    if (!eventAdded) {
                        JOptionPane.showMessageDialog(mainWindow, "Event already exists!", NEW_EVENT, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), NEW_EVENT, JOptionPane.ERROR_MESSAGE);
                } finally {
                    confirmation();
                    clearFields();
                }
            }
        });
        createButton.setMnemonic(KeyEvent.VK_ENTER);
        createButton.setEnabled(false);
    }

    /**
     * Create clear button.
     */
    private void createClearButton() {
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        clearButton.setMnemonic(KeyEvent.VK_C);
        clearButton.setEnabled(false);
    }

    /**
     * Confirmation Dialog.
     */
    private void confirmation() {
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Confirm event creation?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            controller.createEvent();
            JOptionPane.showMessageDialog(mainWindow, "Event created with success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            usersJL.clearSelection();
            selectedUsers(false);
            mainWindow.updateTabs();
            mainWindow.updateMenu();
        }
    }

    /**
     * Clear text fields.
     */
    private void clearFields() {
        txtDescription.setText("");
        txtName.setText("");
        buttonGroup.clearSelection();
        eventEnd.setCalendar(null);
        eventStart.setCalendar(null);
        submissionStart.setCalendar(null);
        submissionEnd.setCalendar(null);
        somethingFilled();
    }
}
