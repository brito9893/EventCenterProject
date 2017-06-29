package lapr.project.ui;

import lapr.project.controller.AssignStandToApplicationController;
import lapr.project.controller.DecideApplicationController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mário Vaz on 21-Jun-17.
 */
public class CreateApplicationDetailsPanelUI extends JPanel {

    /**
     * Label Size Dimension
     */
    private static final Dimension LABEL_SIZE = new JLabel("2-5 Keywords (use ',' to separate):").getPreferredSize();

    /**
     * Text Field Width
     */
    private static final int TEXT_FIELD_WIDTH = 20;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4262144227288906528L;

    /**
     * Class constructor
     *
     * @param controller        Controller
     * @param objectClassString Controller name String
     */
    public CreateApplicationDetailsPanelUI(Object controller, String objectClassString) {
        super();

        setLayout(new GridLayout(5, 1));

        if ("AssignStandToApplicationController".equals(objectClassString)) {
            add(createCompanyPanel(((AssignStandToApplicationController) controller).getEventsAndApplicationsController().getApplicationCompany()));
            add(createDescriptionPanel(((AssignStandToApplicationController) controller).getEventsAndApplicationsController().getApplicationDescription()));
            add(createKeywordsPanel(((AssignStandToApplicationController) controller).getEventsAndApplicationsController().getApplicationKeywords()));
            add(createInvitesPanel(((AssignStandToApplicationController) controller).getEventsAndApplicationsController().getApplicationInvites()));
            add(createStandAreaPanel(((AssignStandToApplicationController) controller).getEventsAndApplicationsController().getApplicationStandArea()));
        } else {
            add(createCompanyPanel(((DecideApplicationController) controller).getEventsAndApplicationsController().getApplicationCompany()));
            add(createDescriptionPanel(((DecideApplicationController) controller).getEventsAndApplicationsController().getApplicationDescription()));
            add(createKeywordsPanel(((DecideApplicationController) controller).getEventsAndApplicationsController().getApplicationKeywords()));
            add(createInvitesPanel(((DecideApplicationController) controller).getEventsAndApplicationsController().getApplicationInvites()));
            add(createStandAreaPanel(((DecideApplicationController) controller).getEventsAndApplicationsController().getApplicationStandArea()));
        }
    }

    /**
     * Create panel display company's name.
     *
     * @return panel to display company's name
     */
    private static JPanel createCompanyPanel(String company) {
        JLabel lbl = new JLabel("Company's name:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        JTextField txtCompany = new JTextField(company, TEXT_FIELD_WIDTH);
        txtCompany.setEditable(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtCompany);

        return p;
    }

    /**
     * Create panel to display application's description.
     *
     * @return panel to display application's description.
     */
    private static JPanel createDescriptionPanel(String description) {
        JLabel lbl = new JLabel("Description:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        JTextField txtDescription = new JTextField(description, TEXT_FIELD_WIDTH);
        txtDescription.setEditable(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtDescription);

        return p;
    }

    /**
     * Create panel to display application's keywords.
     *
     * @return panel to display application's keywords.
     */
    private static JPanel createKeywordsPanel(String keywords) {
        JLabel lbl = new JLabel("2-5 Keywords (use ';' to separate):", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        JTextField txtKeywords = new JTextField(keywords, TEXT_FIELD_WIDTH);
        txtKeywords.setEditable(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(txtKeywords);

        return p;
    }

    /**
     * Create panel display application's invites.
     *
     * @return panel to display application's invites
     */
    private static JPanel createInvitesPanel(int invites) {
        JLabel lbl = new JLabel("Invites:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        JTextField intInvites = new JTextField(Integer.toString(invites), TEXT_FIELD_WIDTH);
        intInvites.setEditable(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intInvites);

        return p;
    }

    /**
     * Create panel display stand's area.
     *
     * @return panel to display stand's area.
     */
    private static JPanel createStandAreaPanel(int standArea) {
        JLabel lbl = new JLabel("Stand's area:", JLabel.RIGHT);
        lbl.setPreferredSize(LABEL_SIZE);

        JTextField intStandArea = new JTextField(Integer.toString(standArea), TEXT_FIELD_WIDTH);
        intStandArea.setEditable(false);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p.add(lbl);
        p.add(intStandArea);

        return p;
    }
}
