package lapr.project.ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import lapr.project.controller.DecideApplicationController;
import lapr.project.model.EventCenter;
import lapr.project.model.FAE;

/**
 * Decide Application UI
 *
 * @author Liliana e Luis
 */
class DecideApplicationUI extends JPanel {

    /**
     * Label Size Dimension
     */
    private static final Dimension LABEL_SIZE = new JLabel("2-5 Keywords (use ',' to separate):").getPreferredSize();

    /**
     * SpinnerNumber Model minimum value
     */
    private static final int MIN_VALUE = 0;

    /**
     * SpinnerNumber Model maximum value
     */
    private static final int MAX_VALUE = 5;

    /**
     * SpinnerNumber Model step value
     */
    private static final int STEP_VALUE = 1;

    /**
     * SpinnerNumber Model initial value
     */
    private static final int INIT_VALUE = 0;

    /**
     * Accept String
     */
    private static final String ACCEPT = "Accept";

    /**
     * Deny String
     */
    private static final String DENY = "Deny";

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7019583214437159952L;
    /**
     * New Decision String
     */
    private static final String NEW_DECISION = "New Decision";
    /**
     * Decide application controller
     */
    private DecideApplicationController controller;
    /**
     * Show details button
     */
    private JButton showDetailsButton;
    /**
     * Decide button
     */
    private JButton decideButton;
    /**
     * Confirm button
     */
    private JButton confirmButton;
    /**
     * Clear button
     */
    private JButton clearButton;
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
     * Knowledge JSpinner
     */
    private JSpinner intKnowledge;
    /**
     * Application Adequacy JSpinner
     */
    private JSpinner intApplicationAdequacy;
    /**
     * Event Adequacy JSpinner
     */
    private JSpinner intEventAdequacy;
    /**
     * Overall JSpinner
     */
    private JSpinner intOverall;
    /**
     * Decision JSpinner
     */
    private JSpinner txtDecision;
    /**
     * Justification TextArea
     */
    private JTextArea txtJustification;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    DecideApplicationUI(EventCenter eventCenter, FAE currentUser, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new DecideApplicationController(eventCenter, currentUser);

        eventsModel = new DefaultListModel<>();
        applicationsModel = new DefaultListModel<>();

        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (eventsJL.getSelectedIndex() != -1 && !"No events to show".equals(eventID)) {
                controller.getEventsAndApplicationsController().setEvent(eventID);
                updateApplicationModel();
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
        add(createDecideApplicationPanel(), BorderLayout.SOUTH);
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
        decide(false);
        showDetailsButton.setEnabled(false);
        decideButton.setEnabled(false);
        String eventID = eventsJL.getSelectedValue();
        if (eventID != null && !"No events to show".equals(eventID)) {
            List<String> applicationsList = controller.getEventsAndApplicationsController().getApplications();
            for (String a : applicationsList) {
                applicationsModel.addElement(a);
            }
        }
    }

    /**
     * Set Decide buttons and JTexts enabled/editable or not.
     *
     * @param value what defines if JTexts are enabled or not.
     */
    private void decide(boolean value) {
        clearFields();
        showDetailsButton.setEnabled(!value);
        decideButton.setEnabled(!value);
        intKnowledge.setEnabled(value);
        intApplicationAdequacy.setEnabled(value);
        intEventAdequacy.setEnabled(value);
        intOverall.setEnabled(value);
        txtDecision.setEnabled(value);
        txtJustification.setEditable(value);
        confirmButton.setEnabled(value);
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
                if (!"No applications to show".equals(applicationID)) {
                    controller.getEventsAndApplicationsController().setApplication(applicationID);
                    clearFields();
                    decide(false);
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
     * Create application buttons panel
     *
     * @return application buttons panel
     */
    private JPanel createApplicationButtonsPanel() {
        createShowDetailsButton();
        createDecideButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(showDetailsButton);
        p.add(decideButton);
        return p;
    }

    /**
     * Create show details button.
     */
    private void createShowDetailsButton() {
        showDetailsButton = new JButton("Show application details");
        showDetailsButton.addActionListener(e -> JOptionPane.showMessageDialog(mainWindow, new CreateApplicationDetailsPanelUI(controller, "DecideApplicationController"), "Application details", JOptionPane.INFORMATION_MESSAGE));
        showDetailsButton.setEnabled(false);
    }

    /**
     * Create decide button.
     */
    private void createDecideButton() {
        decideButton = new JButton("Decide");
        decideButton.addActionListener(e -> decide(true));
        decideButton.setEnabled(false);
    }

    /**
     * Create decide application panel.
     *
     * @return decide application panel.
     */
    private JPanel createDecideApplicationPanel() {
        JPanel decideApplicationPanel = new JPanel(new BorderLayout());
        decideApplicationPanel.setBorder(new TitledBorder("Decide Application:"));

        decideApplicationPanel.add(createDecideApplicationOptionsPanel(), BorderLayout.CENTER);
        decideApplicationPanel.add(createDecideApplicationButtonsPanel(), BorderLayout.SOUTH);

        return decideApplicationPanel;
    }

    /**
     * Create decide application options panel.
     *
     * @return decide application options panel.
     */
    private JPanel createDecideApplicationOptionsPanel() {
        JPanel decideApplicationOptionsPanel = new JPanel(new GridLayout(3, 2));

        decideApplicationOptionsPanel.add(createKnowledgePanel());
        decideApplicationOptionsPanel.add(createApplicationAdequacyPanel());
        decideApplicationOptionsPanel.add(createEventAdequacyPanel());
        decideApplicationOptionsPanel.add(createOverallPanel());
        decideApplicationOptionsPanel.add(createDecisionPanel());
        decideApplicationOptionsPanel.add(createJustificationPanel());

        return decideApplicationOptionsPanel;
    }

    /**
     * Check is something is filled
     */
    private void anythingFill() {
        if (txtJustification.getText().length() == 0 && (int) intKnowledge.getValue() == 0) {
            if ((int) intApplicationAdequacy.getValue() == 0 && (int) intEventAdequacy.getValue() == 0 && (int) intOverall.getValue() == 0) {
                clearButton.setEnabled(false);
            } else {
                clearButton.setEnabled(true);
            }
        } else {
            clearButton.setEnabled(true);
        }
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set FAE's Knowledge about topic.
     *
     * @return panel to set FAE's Knowledge about topic
     */
    private JPanel createKnowledgePanel() {
        JLabel lbl = new JLabel("FAE's Knowledge about topic", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel knowledgeModel = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intKnowledge = new JSpinner(knowledgeModel);
        intKnowledge.setEnabled(false);

        JComponent comp = intKnowledge.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intKnowledge.addChangeListener(e -> anythingFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intKnowledge);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set Application adequacy.
     *
     * @return panel to set Application adequacy
     */
    private JPanel createApplicationAdequacyPanel() {
        JLabel lbl = new JLabel("Application adequacy", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel applicationAdequacyModel = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intApplicationAdequacy = new JSpinner(applicationAdequacyModel);
        intApplicationAdequacy.setEnabled(false);

        JComponent comp = intApplicationAdequacy.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intApplicationAdequacy.addChangeListener(e -> anythingFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intApplicationAdequacy);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set Event adequacy.
     *
     * @return panel to set Event adequacy
     */
    private JPanel createEventAdequacyPanel() {
        JLabel lbl = new JLabel("Event adequacy", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel eventAdequacy = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intEventAdequacy = new JSpinner(eventAdequacy);
        intEventAdequacy.setEnabled(false);

        JComponent comp = intEventAdequacy.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intEventAdequacy.addChangeListener(e -> anythingFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intEventAdequacy);

        return p;
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set Overall recommendation.
     *
     * @return panel to set Overall recommendation
     */
    private JPanel createOverallPanel() {
        JLabel lbl = new JLabel("Overall recommendation", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        SpinnerModel overallModel = new SpinnerNumberModel(INIT_VALUE, MIN_VALUE, MAX_VALUE, STEP_VALUE);

        intOverall = new JSpinner(overallModel);
        intOverall.setEnabled(false);

        JComponent comp = intOverall.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        intOverall.addChangeListener(e -> anythingFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intOverall);

        return p;
    }

    /**
     * Create justification panel with textArea.
     *
     * @return justification panel with textArea
     */
    private JPanel createJustificationPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Justification"));
        createTxtJustification();
        JScrollPane scrollPane = new JScrollPane(txtJustification);
        p.add(scrollPane, BorderLayout.CENTER);
        txtJustification.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                anythingFill();
            }
        });
        return p;
    }

    /**
     * Create text Area to set justification.
     */
    private void createTxtJustification() {
        txtJustification = new JTextArea(2, 1);
        txtJustification.setLineWrap(true);
        txtJustification.setFont(new Font(txtJustification.getFont().getName(), txtJustification.getFont().getStyle(), 14));
        txtJustification.setEditable(false);
    }

    /**
     * Create panel with descriptive JLabel and JSpinner to set Decision.
     *
     * @return panel to set Decision
     */
    private JPanel createDecisionPanel() {
        JLabel lbl = new JLabel("Overall recommendation", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        List<String> decisions = new ArrayList<>();
        decisions.add(ACCEPT);
        decisions.add(DENY);

        SpinnerModel overallModel = new SpinnerListModel(decisions);

        txtDecision = new JSpinner(overallModel);
        txtDecision.setEnabled(false);

        txtDecision.addChangeListener(e -> anythingFill());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtDecision);

        return p;
    }

    /**
     * Create decide application buttons panel.
     *
     * @return decide application buttons panel.
     */
    private JPanel createDecideApplicationButtonsPanel() {
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
            List<Integer> rates = new ArrayList<>();
            rates.add((int) intKnowledge.getValue());
            rates.add((int) intApplicationAdequacy.getValue());
            rates.add((int) intEventAdequacy.getValue());
            rates.add((int) intOverall.getValue());

            String stringDecision = (String) txtDecision.getValue();
            boolean decision = true;
            if (stringDecision.equals(DENY)) {
                decision = false;
            }
            String justification = txtJustification.getText();

            if ("".equals(stringDecision) || "".equals(justification)) {
                JOptionPane.showMessageDialog(mainWindow, "Incomplete information", NEW_DECISION, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean decisionAdded = controller.setDecision(rates, decision, justification);
                    if (!decisionAdded) {
                        JOptionPane.showMessageDialog(mainWindow, "Application already exists!", NEW_DECISION, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), NEW_DECISION, JOptionPane.ERROR_MESSAGE);
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
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Confirm decision submission?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            controller.submitDecision();
            JOptionPane.showMessageDialog(mainWindow, "Decision submitted with success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            eventsJL.clearSelection();
            applicationsJL.clearSelection();
            decide(false);
            updateEventModel();
            mainWindow.updateTabs();
        }
    }

    /**
     * Clear text fields.
     */
    private void clearFields() {
        intKnowledge.setValue(0);
        intApplicationAdequacy.setValue(0);
        intEventAdequacy.setValue(0);
        intOverall.setValue(0);
        txtDecision.setValue(ACCEPT);
        txtJustification.setText("");
        decideButton.setEnabled(false);
        showDetailsButton.setEnabled(false);
        clearButton.setEnabled(false);
    }
}