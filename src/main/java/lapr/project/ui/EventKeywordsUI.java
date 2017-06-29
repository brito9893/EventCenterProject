/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import lapr.project.controller.ShowSubmissionKeywordsController;
import lapr.project.model.EventCenter;

/**
 * @author U
 */
public class EventKeywordsUI extends JPanel {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 1L;

    /**
     * Show submission keywords controller
     */
    private ShowSubmissionKeywordsController controller;

    /**
     * JTable
     */
    private JTable table;

    /**
     * Model Table
     */
    private DefaultTableModel modelTable;

    /**
     * Events ComboBox
     */
    private JComboBox<String> comboEvents;

    /**
     * ComboBox Model
     */
    private DefaultComboBoxModel<String> comboModel;

    /**
     * Class constructor
     *
     * @param center Event Center
     */
    public EventKeywordsUI(EventCenter center) {
        this.controller = new ShowSubmissionKeywordsController(center);
        this.setLayout(new BorderLayout());
        createComponents();
        this.setVisible(true);
    }

    /**
     * Create Components
     */
    private void createComponents() {
        table = new JTable();
        modelTable = new DefaultTableModel();
        table.setModel(modelTable);
        table.setEnabled(false);
        modelTable.addColumn("Keyword");
        modelTable.addColumn("Frequency");
        JScrollPane scrollPane = new JScrollPane(table);
        add(createComboBox(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.SOUTH);
    }

    /**
     * Create ComboBox Panel
     *
     * @return ComboBox Panel
     */
    private JPanel createComboBox() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboModel = new DefaultComboBoxModel<>();
        comboEvents = new JComboBox<>(comboModel);
        updateComboModel();
        comboEvents.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                //do nothing
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                String event = (String) comboEvents.getSelectedItem();
                updateTableInfo(controller.getEventKeywords(event));
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
                //do nothing
            }
        });

        p.add(new JLabel("Event"));
        p.add(comboEvents);

        return p;
    }

    /**
     * Update table info
     *
     * @param map Map<String, Double>
     */
    private void updateTableInfo(Map<String, Double> map) {
        modelTable.setRowCount(0);
        table.removeAll();

        map.forEach((key, kf) -> {
            double frequency = Math.round(kf * 1000.0) / 10.0;
            Object[] row = {key, frequency + " %"};
            modelTable.addRow(row);
        });
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

    }

    /**
     * Update ComboBox Model
     */
    private void updateComboModel() {
        controller.getEvents().forEach(e -> comboModel.addElement(e));
    }
}
