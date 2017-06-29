package lapr.project.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Mário Vaz on 21-Jun-17.
 */
public class SelectEventPanelUI extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7437453851873354400L;

    /**
     * Events JList
     */
    private JList<String> eventsJL;

    /**
     * Class constructor
     *
     * @param eventsJL events JList
     */
    public SelectEventPanelUI(JList<String> eventsJL) {
        super();

        this.eventsJL = eventsJL;

        setLayout(new BorderLayout());
        add(createEventsPanel(), BorderLayout.CENTER);
    }

    /**
     * Create events panel that contains the eventsJL.
     *
     * @return events panel.
     */
    private JPanel createEventsPanel() {
        JPanel eventsPanel = new JPanel(new BorderLayout());
        eventsJL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrPane = new JScrollPane(eventsJL);
        eventsPanel.add(scrPane, BorderLayout.CENTER);
        eventsPanel.setBorder(new TitledBorder("Select event:"));
        return eventsPanel;
    }
}
