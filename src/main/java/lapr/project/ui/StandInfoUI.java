/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import lapr.project.controller.ShowStandInfoController;

/**
 * @author U
 */
public class StandInfoUI extends JPanel {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 12L;
    /**
     * Mean String
     */
    private static final String MEAN = "Mean = ";
    /**
     * Standard Deviation String
     */
    private static final String STANDARD_DEVIATION = "Standard Deviation = ";
    /**
     * Show stand info controller
     */
    private ShowStandInfoController controller;
    /**
     * Default Table Model
     */
    private DefaultTableModel model;
    /**
     * Event String
     */
    private String event;

    /**
     * Class constructor
     *
     * @param controller Show stand info controller
     * @param event      Event String
     */
    public StandInfoUI(ShowStandInfoController controller, String event) {
        this.controller = controller;
        this.event = event;
        this.setLayout(new BorderLayout());
        addComponent();
        this.setVisible(true);
    }

    /**
     * Create Component
     */
    private void addComponent() {
        model = new DefaultTableModel();
        JTable tabel = new JTable(model);
        tabel.setEnabled(false);
        JScrollPane scroll = new JScrollPane(tabel);
        if (addInfoTable()) {
            add(scroll, BorderLayout.NORTH);
            String mean = MEAN + Math.round(controller.calculateMeanStandArea() * 100.0) / 100.0;
            String deviation = STANDARD_DEVIATION + Math.round(controller.calculateStandardDeviation() * 100.0) / 100.0;
            add(new JLabel(mean), BorderLayout.CENTER);
            add(new JLabel(deviation), BorderLayout.SOUTH);
        }

    }

    /**
     * Create Info Table
     */
    private boolean addInfoTable() {
        model.addColumn("Stand Area");
        model.addColumn("Absolute frenquency");
        model.addColumn("Relative frenquency");

        String[][] array = controller.getInfoStands(event);
        if (array == null) {
            add(new JLabel("No information avaliable"));
            return false;
        } else {
            for (int a = 0; a < array.length; a++) {
                model.addRow(array[a]);
            }
            return true;
        }

    }
}
