package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

class CreateStandUI extends JPanel {

    /**
     * Label Size Dimension
     */
    private static final Dimension LABEL_SIZE = new JLabel("Stand's area:").getPreferredSize();

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
    private static final long serialVersionUID = 2435394575495170966L;
    /**
     * New Stand String
     */
    private static final String NEW_STAND = "New Stand";
    /**
     * Create stand controller
     */
    private CreateStandController controller;
    /**
     * Create button
     */
    private JButton createButton;
    /**
     * Clear button
     */
    private JButton clearButton;
    /**
     * Events JList
     */
    private JList<String> eventsJL;
    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;
    /**
     * Description JTextField
     */
    private JTextField txtDescription;
    /**
     * Stand Area JSpinner
     */
    private JSpinner intStandArea;
    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Class constructor.
     *
     * @param eventCenter Event Center.
     */
    CreateStandUI(EventCenter eventCenter, Window mainWindow) {
        super();

        setLayout(new BorderLayout());

        this.mainWindow = mainWindow;

        controller = new CreateStandController(eventCenter);

        eventsModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);
        eventsJL.addListSelectionListener(e -> {
            String eventID = eventsJL.getSelectedValue();
            if (eventID != null && !"No events to show".equals(eventID)) {
                controller.getEventsAndApplicationsController().setEvent(eventID);
                selectedEvent(true);
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
        add(createNewStandPanel(), BorderLayout.SOUTH);
    }

    /**
     * Update events model.
     */
    private void updateEventsModel() {
        List<String> eventList = controller.getEventsAndApplicationsController().getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
    }

    /**
     * Check if something is filled
     */
    private void anythingFilled() {
        if (txtDescription.getText().length() == 0 && (int) intStandArea.getValue() == 0) {
            clearButton.setEnabled(false);
        } else {
            clearButton.setEnabled(true);
        }
    }

    /**
     * Set new Stand buttons and JTexts enabled/editable or not.
     *
     * @param value what defines if JTexts are enabled or not.
     */
    private void selectedEvent(boolean value) {
        txtDescription.setEditable(value);
        createButton.setEnabled(value);
        intStandArea.setEnabled(value);
        anythingFilled();
    }

    /**
     * Create new stand panel.
     *
     * @return new stand panel.
     */
    private JPanel createNewStandPanel() {
        JPanel newStandPanel = new JPanel(new GridLayout(3, 1));
        newStandPanel.setBorder(new TitledBorder("New Stand:"));

        newStandPanel.add(createDescriptionPanel());
        newStandPanel.add(createAreaPanel());
        newStandPanel.add(createNewStandButtonsPanel());

        return newStandPanel;
    }

    /**
     * Create panel with descriptive JLabel and JTextField to set stand's description.
     *
     * @return panel to set stand's description.
     */
    private JPanel createDescriptionPanel() {
        JLabel lbl = new JLabel("Description:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        txtDescription = new JTextField(TEXT_FIELD_WIDTH);
        txtDescription.setEditable(false);
        txtDescription.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                anythingFilled();
            }
        });

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtDescription);

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
        intStandArea.addChangeListener(e -> anythingFilled());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intStandArea);

        return p;
    }

    /**
     * Create new stand buttons panel.
     *
     * @return new stand buttons panel.
     */
    private JPanel createNewStandButtonsPanel() {
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
            String description = txtDescription.getText();
            int standArea = (int) intStandArea.getValue();

            if ("".equals(description) || standArea == 0) {
                JOptionPane.showMessageDialog(mainWindow, "Incomplete information", NEW_STAND, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean standAdded = controller.setStand(description, standArea);
                    if (!standAdded) {
                        JOptionPane.showMessageDialog(mainWindow, "Stand already exists!", NEW_STAND, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), NEW_STAND, JOptionPane.ERROR_MESSAGE);
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
        int answer = JOptionPane.showConfirmDialog(mainWindow, "Confirm stand creation?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            controller.createStand();
            JOptionPane.showMessageDialog(mainWindow, "Stand created with success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            eventsJL.clearSelection();
            selectedEvent(false);
            mainWindow.updateTabs();
        }
    }

    /**
     * Clear text fields.
     */
    private void clearFields() {
        txtDescription.setText("");
        intStandArea.setValue(0);
    }
}
