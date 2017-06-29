package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Created by Mário Vaz on 19-Jun-17.
 */
public class TestTheDifferenceBetweenTwoEventsAcceptanceRateUI {

    /**
     * Compare Events acceptance String
     */
    private static final String COMPARE_EVENTS = "Compare Events acceptance";
    /**
     * Level 1 String
     */
    private static final String LEVEL_1 = "Level 1";
    /**
     * Number of events to select
     */
    private static final int EVENTS_TO_SELECT = 2;
    /**
     * Main Window
     */
    private Window mainWindow;
    /**
     * Test the difference between two events acceptance rate controller
     */
    private TestTheDifferenceBetweenTwoEventsAcceptanceRateController controller;
    /**
     * Level type
     */
    private String level;
    /**
     * Events Model
     */
    private DefaultListModel<String> eventsModel;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Select Button
     */
    private JButton selectButton;

    /**
     * Events ID's List that contain both event ID's
     */
    private List<String> eventsIDS;

    /**
     * Class constructor
     *
     * @param eventCenter Event Center
     * @param mainWindow  Main Window
     * @param level       Level type
     */
    public TestTheDifferenceBetweenTwoEventsAcceptanceRateUI(EventCenter eventCenter, Window mainWindow, String level) {

        this.level = level;
        this.mainWindow = mainWindow;

        controller = new TestTheDifferenceBetweenTwoEventsAcceptanceRateController(eventCenter);

        eventsModel = new DefaultListModel<>();
        eventsJL = new JList<>(eventsModel);

        createComponents();
    }

    /**
     * Create Components
     */
    private void createComponents() {
        updateEventsModel();
        createJDialog();
    }

    /**
     * Update events model.
     */
    private void updateEventsModel() {
        java.util.List<String> eventList = controller.getEvents();
        for (String e : eventList) {
            eventsModel.addElement(e);
        }
    }

    /**
     * Create main JDialog
     */
    private void createJDialog() {
        JDialog jDialog = new JDialog(mainWindow);
        jDialog.setTitle(COMPARE_EVENTS);
        jDialog.setLayout(new BorderLayout());

        jDialog.add(createSelectTwoEventsContent(), BorderLayout.CENTER);

        jDialog.pack();
        //set width depending on title
        FontMetrics fm = jDialog.getFontMetrics(jDialog.getFont());
        int width = fm.stringWidth(jDialog.getTitle()) + 75;
        width = Math.max(width, jDialog.getPreferredSize().width);
        jDialog.setSize(new Dimension(width, jDialog.getPreferredSize().height));

        jDialog.setLocationRelativeTo(mainWindow);
        jDialog.setModal(true);
        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Create select events panel.
     *
     * @return select events panel.
     */
    private JPanel createSelectTwoEventsContent() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createEventsPanel(), BorderLayout.CENTER);
        p.add(createEventsButtonPanel(), BorderLayout.SOUTH);
        return p;
    }

    /**
     * Create events panel that contains the eventsJL.
     *
     * @return events panel.
     */
    private JPanel createEventsPanel() {
        JPanel eventsPanel = new JPanel(new BorderLayout());

        eventsJL.setSelectionModel(new DefaultListSelectionModel() {
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

        eventsJL.addListSelectionListener(e -> {
            eventsIDS = eventsJL.getSelectedValuesList();
            if (eventsIDS != null && eventsJL.getSelectedIndex() != -1 && !"No events to show".equals(eventsIDS.get(0))) {
                selectButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(eventsJL);

        eventsPanel.add(scrPane, BorderLayout.CENTER);
        eventsPanel.setBorder(new TitledBorder("Select events:"));

        return eventsPanel;
    }

    /**
     * Create events button
     *
     * @return events button
     */
    private JPanel createEventsButtonPanel() {
        createSelectButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(selectButton);

        return p;
    }

    /**
     * Create select organizers button
     */
    private void createSelectButton() {
        selectButton = new JButton("Select events");
        selectButton.addActionListener(e -> {
            eventsIDS = eventsJL.getSelectedValuesList();
            if (eventsIDS.size() != EVENTS_TO_SELECT || eventsJL.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(mainWindow, "You must select TWO (2) events", COMPARE_EVENTS, JOptionPane.ERROR_MESSAGE);
            } else {
                controller.setEvents(eventsIDS.get(0), eventsIDS.get(1));

                double test = controller.calculateTheDifferenceBetweenTheTwoEventsAcceptanceRate();
                boolean level1 = controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1(test);
                boolean level5 = controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5(test);
                String result = (level.equals(LEVEL_1)) ? String.valueOf(level1) : String.valueOf(level5);

                String message = String.format("Event 1: %s%nEvent 2: %s%nSample Event 1: %d%nSample Event 2: %d%n"
                                + "Critical Value(zc): %.3f%nObserved Value Of statistic: %.3f%n"
                                + "There are differences: %s",
                        eventsIDS.get(0), eventsIDS.get(1)
                        , TestTheDifferenceBetweenTwoEventsAcceptanceRateController.SAMPLE,
                        TestTheDifferenceBetweenTwoEventsAcceptanceRateController.SAMPLE,
                        (level.equals(LEVEL_1)) ? 2.575 : 1.96, test, result);
                JOptionPane.showMessageDialog(mainWindow, message, COMPARE_EVENTS, JOptionPane.DEFAULT_OPTION);


                selectButton.setEnabled(false);
                eventsJL.clearSelection();
            }
        });
        selectButton.setMnemonic(KeyEvent.VK_S);
        selectButton.setEnabled(false);
    }
}
