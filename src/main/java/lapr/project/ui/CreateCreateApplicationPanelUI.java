package lapr.project.ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import lapr.project.controller.ChangeOrRemoveApplicationController;
import lapr.project.controller.SubmitApplicationController;

/**
 * Created by Mário Vaz on 20-Jun-17.
 */
public class CreateCreateApplicationPanelUI extends JPanel {

    /**
     * Label Size Dimension
     */
    private static final Dimension LABEL_SIZE = new JLabel("2-5 Keywords (use ',' to separate):").getPreferredSize();

    /**
     * Text Field Width
     */
    private static final int TEXT_FIELD_WIDTH = 20;

    /**
     * SpinnerNumber Model minimum value
     */
    private static final int MIN_VALUE = 0;

    /**
     * SpinnerNumber Model maximum value
     */
    private static final int MAX_VALUE = 1000;

    /**
     * SpinnerNumber Model step value
     */
    private static final int STEP_VALUE = 1;

    /**
     * SpinnerNumber Model initial value
     */
    private static final int INIT_VALUE = 0;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7908418949499905102L;
    /**
     * Submit Application Controller String
     */
    private static final String SUBMIT_APPLICATION_CONTROLLER = "SubmitApplicationController";
    /**
     * New Application String
     */
    private static final String NEW_APPLICATION = "New Application";
    /**
     * Delete application button
     */
    private JButton deleteApplicationButton;
    /**
     * Change application button
     */
    private JButton changeApplicationButton;
    /**
     * Confirm button
     */
    private JButton confirmButton;
    /**
     * Clear button
     */
    private JButton clearButton;
    /**
     * Cancel button
     */
    private JButton cancelButton;
    /**
     * Company TextField
     */
    private JTextField txtCompany;
    /**
     * Description TextField
     */
    private JTextField txtDescription;
    /**
     * Keywords TextField
     */
    private JTextField txtKeywords;
    /**
     * Invites JSpinner
     */
    private JSpinner intInvites;
    /**
     * Stand area JSpinner
     */
    private JSpinner intStandArea;
    /**
     * Main Window
     */
    private Window mainWindow;
    /**
     * Controller
     */
    private transient Object controller;
    /**
     * Events JList
     */
    private JList<String> eventsJL;
    /**
     * Applications JList
     */
    private JList<String> applicationsJL;
    /**
     * Action verb in present String
     */
    private String actionPresent;
    /**
     * Action verb in past String
     */
    private String actionPast;
    /**
     * Controller name String
     */
    private String objectClassString;
    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;
    /**
     * Applications Model
     */
    private DefaultListModel<String> applicationsModel;

    /**
     * Class Constructor
     *
     * @param mainWindow        Main Window
     * @param border            Border String Title
     * @param object            Controller
     * @param objectClassString Controller name String
     * @param eventsJL          Events JList
     */
    public CreateCreateApplicationPanelUI(Window mainWindow, String border, Object object, String objectClassString, JList<String> eventsJL) {
        super();
        setLayout(new GridLayout(6, 1));
        setBorder(new TitledBorder(border + ":"));

        this.objectClassString = objectClassString;

        if (SUBMIT_APPLICATION_CONTROLLER.equals(objectClassString)) {
            actionPresent = "submission";
            actionPast = "submitted";
        } else {
            actionPresent = "change";
            actionPast = "changed";
        }

        this.controller = object;
        this.mainWindow = mainWindow;
        this.eventsJL = eventsJL;

        add(createCompanyPanel());
        add(createDescriptionPanel());
        add(createKeywordsPanel());
        add(createInvitesPanel());
        add(createAreaPanel());
        add(createNewApplicationButtonsPanel());
    }

    /**
     * Check if everything is empty
     *
     * @param company     Company name
     * @param description Description
     * @param keywords    Keywords String
     * @param invites     Invites
     * @param standArea   Stand's area
     * @return Logic value of success of operation
     */
    private static boolean anythingEmpty(String company, String description, String keywords, int invites, int standArea) {
        return "".equals(company) || "".equals(description) || "".equals(keywords) || invites == 0 || standArea == 0;
    }

    /**
     * Set Change Application Objects
     *
     * @param changeApplicationButton Change application button
     * @param deleteApplicationButton Delete application button
     * @param eventsModel             Events Model
     * @param applicationsModel       Applications Model
     * @param applicationsJL          Applications JList
     * @param cancelButton            Cancel button
     */
    public void setChangeApplicationObjects(JButton changeApplicationButton, JButton deleteApplicationButton, DefaultListModel<String> eventsModel, DefaultListModel<String> applicationsModel, JList<String> applicationsJL, JButton cancelButton) {
        this.changeApplicationButton = changeApplicationButton;
        this.deleteApplicationButton = deleteApplicationButton;
        this.eventsModel = eventsModel;
        this.applicationsModel = applicationsModel;
        this.applicationsJL = applicationsJL;
        this.cancelButton = cancelButton;
    }

    /**
     * Check if anything is filled
     */
    private void verifyFill() {
        if (txtCompany.getText().length() == 0 && txtDescription.getText().length() == 0 && txtKeywords.getText().length() == 0) {
            if ((int) intInvites.getValue() == 0 && (int) intStandArea.getValue() == 0) {
                clearButton.setEnabled(false);
            } else {
                clearButton.setEnabled(true);
            }
        } else {
            clearButton.setEnabled(true);
        }
    }

    /**
     * Set Application buttons and JTexts enabled/editable or not.
     *
     * @param value what defines if JTexts are enabled or not.
     */
    public void setButtons(boolean value) {
        txtCompany.setEditable(value);
        txtDescription.setEditable(value);
        txtKeywords.setEditable(value);
        confirmButton.setEnabled(value);
        intInvites.setEnabled(value);
        intStandArea.setEnabled(value);
        verifyFill();
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set company's name.
     *
     * @return panel to set company's name
     */
    private JPanel createCompanyPanel() {
        JLabel lbl = new JLabel("Company's name:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        txtCompany = new JTextField(TEXT_FIELD_WIDTH);
        txtCompany.setEditable(false);
        txtCompany.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verifyFill();
            }
        });

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtCompany);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set application's description.
     *
     * @return panel to set application's description.
     */
    private JPanel createDescriptionPanel() {
        JLabel lbl = new JLabel("Description:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        txtDescription = new JTextField(TEXT_FIELD_WIDTH);
        txtDescription.setEditable(false);
        txtDescription.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verifyFill();
            }
        });

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtDescription);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set application's keywords.
     *
     * @return panel to set application's keywords.
     */
    private JPanel createKeywordsPanel() {
        JLabel lbl = new JLabel("2-5 Keywords (use ';' to separate):", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        txtKeywords = new JTextField(TEXT_FIELD_WIDTH);
        txtKeywords.setEditable(false);
        txtKeywords.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verifyFill();
            }
        });

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtKeywords);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set company's invites.
     *
     * @return panel to set company's invites
     */
    private JPanel createInvitesPanel() {
        JLabel lbl = new JLabel("Invites:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel invitesModel = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intInvites = new JSpinner(invitesModel);
        intInvites.setEnabled(false);

        JComponent comp = intInvites.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intInvites.addChangeListener(e -> verifyFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intInvites);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set company's stand's area.
     *
     * @return panel to set company's stand's area
     */
    private JPanel createAreaPanel() {
        JLabel lbl = new JLabel("Stand's area:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel standAreaModel = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intStandArea = new JSpinner(standAreaModel);
        intStandArea.setEnabled(false);

        JComponent comp = intStandArea.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intStandArea.addChangeListener(e -> verifyFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intStandArea);

        return p;
    }

    /**
     * Create new application buttons panel.
     *
     * @return new application buttons panel.
     */
    private JPanel createNewApplicationButtonsPanel() {
        createConfirmButton();

        createClearButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(confirmButton);
        p.add(clearButton);

        return p;
    }

    /**
     * Create confirm button.
     */
    private void createConfirmButton() {
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            String company = txtCompany.getText();
            String description = txtDescription.getText();
            String keywords = txtKeywords.getText();
            int invites = (int) intInvites.getValue();
            int standArea = (int) intStandArea.getValue();

            if (anythingEmpty(company, description, keywords, invites, standArea)) {
                JOptionPane.showMessageDialog(mainWindow, "Incomplete information", NEW_APPLICATION, JOptionPane.ERROR_MESSAGE);
            } else if (!setApplication(company, description, invites, standArea, keywords)) {
                JOptionPane.showMessageDialog(mainWindow, "You must set 2 to 5 keywords.", NEW_APPLICATION, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean canAdded = setApplication(company, description, invites, standArea, keywords);
                    if (!canAdded) {
                        JOptionPane.showMessageDialog(mainWindow, "Application already exists!", NEW_APPLICATION, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), NEW_APPLICATION, JOptionPane.ERROR_MESSAGE);
                } finally {
                    confirmation();
                    clearFields();
                }
            }
        });
        confirmButton.setMnemonic(KeyEvent.VK_ENTER);
        confirmButton.setEnabled(false);
    }

    /**
     * Set application
     *
     * @param company     Company name
     * @param description Description
     * @param invites     Invites
     * @param standArea   Stand's area
     * @param keywords    Keywords String
     * @return Logic value of success of operation
     */
    private boolean setApplication(String company, String description, int invites, int standArea, String keywords) {
        if (SUBMIT_APPLICATION_CONTROLLER.equals(objectClassString)) {
            return ((SubmitApplicationController) controller).setApplication(company, description, invites, standArea, keywords);
        } else {
            return ((ChangeOrRemoveApplicationController) controller).createApplication(company, description, invites, standArea, keywords);
        }
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
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Confirm application " + actionPresent + "?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {

            if (SUBMIT_APPLICATION_CONTROLLER.equals(objectClassString)) {
                ((SubmitApplicationController) controller).createApplication();
            } else {
                ((ChangeOrRemoveApplicationController) controller).changeApplication();
            }
            JOptionPane.showMessageDialog(mainWindow, "Application " + actionPast + " with success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            eventsJL.clearSelection();
            setButtons(false);
            if (!SUBMIT_APPLICATION_CONTROLLER.equals(objectClassString)) {
                updateEventModel();
            }
            mainWindow.updateTabs();
        }
    }

    /**
     * Set change and delete application buttons enabled or not
     *
     * @param value logic value to set buttons enabled or not
     */
    public void setChangeButtons(boolean value) {
        changeApplicationButton.setEnabled(value);
        deleteApplicationButton.setEnabled(value);
    }

    /**
     * Clear text fields.
     */
    public void clearFields() {
        txtCompany.setText("");
        txtDescription.setText("");
        txtKeywords.setText("");
        intStandArea.setValue(0);
        intInvites.setValue(0);
    }

    /**
     * Update Event Model
     */
    private void updateEventModel() {
        eventsModel.removeAllElements();
        applicationsModel.removeAllElements();
        List<String> eventList = ((ChangeOrRemoveApplicationController) controller).getEventsAndApplicationsController().getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
        eventsJL.setEnabled(true);
        applicationsJL.setEnabled(true);
        cancelButton.setEnabled(false);
    }
}
