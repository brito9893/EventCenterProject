package lapr.project.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import lapr.project.controller.TestDifferenceTwoFaeController;
import lapr.project.model.EventCenter;

/**
 * Test the difference between to FAE User Interface
 */
class TestDifferenceTwoFaeUI {

    /**
     * Compare FAEs average ratings String
     */
    private static final String COMPARE_FAE = "Compare FAEs average ratings";
    /**
     * Number of FAEs to select
     */
    private static final int FAES_TO_SELECT = 2;
    /**
     * Level 1 String
     */
    private static final String LEVEL_1 = "Level 1";
    /**
     * Level 5 String
     */
    private static final String LEVEL_5 = "Level 5";
    /**
     * Critical Value
     */
    private double criticalValue;
    /**
     * Test difference two FAEs controller
     */
    private TestDifferenceTwoFaeController controller;

    /**
     * FAEs Model
     */
    private DefaultListModel<String> faesModel;

    /**
     * FAEs JList
     */
    private JList<String> faesJL;

    /**
     * Main Window
     */
    private Window mainWindow;

    /**
     * Main JDialog
     */
    private JDialog jDialog;

    /**
     * FAEs ID's List that contain both FAE ID's
     */
    private List<String> faesIDS;

    /**
     * Select Button
     */
    private JButton selectButton;

    /**
     * Class constructor
     *
     * @param level       Level type
     * @param eventCenter Event Center
     * @param mainWindow  Main Window
     */
    TestDifferenceTwoFaeUI(String level, EventCenter eventCenter, Window mainWindow) {

        this.mainWindow = mainWindow;

        controller = new TestDifferenceTwoFaeController(eventCenter);

        if (LEVEL_1.equals(level)) {
            criticalValue = TestDifferenceTwoFaeController.LEVEL_ONE;
        } else if (LEVEL_5.equals(level)) {
            criticalValue = TestDifferenceTwoFaeController.LEVEL_TWO;
        }

        faesModel = new DefaultListModel<>();
        faesJL = new JList<>(faesModel);

        createComponents();
    }

    /**
     * Create Components
     */
    private void createComponents() {
        updateFaesModel();
        createJDialog();
    }

    /**
     * Update FAEs model.
     */

    private void updateFaesModel() {
        List<String> faesList = controller.getFAEs();
        for (String f : faesList) {
            faesModel.addElement(f);
        }
    }

    /**
     * Create Main JDialog
     */
    private void createJDialog() {
        jDialog = new JDialog(mainWindow);
        jDialog.setTitle(COMPARE_FAE);
        jDialog.setLayout(new BorderLayout());

        jDialog.add(createSelectTwoFaesContent(), BorderLayout.CENTER);

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
     * Create select FAEs panel.
     *
     * @return select FAEs panel.
     */
    private JPanel createSelectTwoFaesContent() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(createFAEsPanel(), BorderLayout.CENTER);
        p.add(createFAEsButtonPanel(), BorderLayout.SOUTH);
        return p;
    }

    /**
     * Create FAEs panel that contains the faesJL.
     *
     * @return FAEs panel.
     */
    private JPanel createFAEsPanel() {
        JPanel faesPanel = new JPanel(new BorderLayout());

        faesJL.setSelectionModel(new DefaultListSelectionModel() {
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

        faesJL.addListSelectionListener(e -> {
            faesIDS = faesJL.getSelectedValuesList();
            if (faesIDS != null && faesJL.getSelectedIndex() != -1 && !("No FAEs to show".equals(faesIDS.get(0)))) {
                selectButton.setEnabled(true);
            }
        });
        JScrollPane scrPane = new JScrollPane(faesJL);

        faesPanel.add(scrPane, BorderLayout.CENTER);
        faesPanel.setBorder(new TitledBorder("Select FAEs:"));

        return faesPanel;
    }

    /**
     * Create FAEs button
     *
     * @return FAEs button
     */
    private JPanel createFAEsButtonPanel() {
        createSelectButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(selectButton);

        return p;
    }

    /**
     * Create select organizers button
     */
    private void createSelectButton() {
        selectButton = new JButton("Select FAEs");
        selectButton.addActionListener(e -> {
            faesIDS = faesJL.getSelectedValuesList();
            if (faesIDS.size() != FAES_TO_SELECT || faesJL.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(mainWindow, "You must select TWO (2) FAEs", COMPARE_FAE, JOptionPane.ERROR_MESSAGE);
            } else {
                String faeI = faesIDS.get(0);
                String faeK = faesIDS.get(1);

                controller.setFAEs(faeI, faeK);

                double testDifference = controller.testDifferenceTwoFae();
                int countI = controller.getCountA();
                int countK = controller.getCountB();
                double deviationsI = controller.getDeviationsI();
                double deviationsK = controller.getDeviationsK();
                boolean checkDifference = controller.checkIfThereIsDifference(testDifference, criticalValue);

                String message = "FAE(1): " + faeI + "\nFAE(2): " + faeK + "\nCount(1): " + countI + "\nCount(2): " + countK
                        + "\nDeviations(1): " + String.format("%.3f", deviationsI) + "\nDeviations(2): " + String.format("%.3f", deviationsK) + "\nCritical Value: " + criticalValue
                        + "\nObserved value of test statistic: " + String.format("%.3f", testDifference) + "\nThere are differences?: " + checkDifference;

                createRateDialog(message, COMPARE_FAE);

                selectButton.setEnabled(false);
                faesJL.clearSelection();
            }
        });
        selectButton.setMnemonic(KeyEvent.VK_S);
        selectButton.setEnabled(false);
    }

    /**
     * Create ShowMessageDialog to given rate
     */
    private void createRateDialog(String message, String rateName) {
        JOptionPane.showMessageDialog(jDialog, message, rateName, JOptionPane.INFORMATION_MESSAGE);
    }
}
