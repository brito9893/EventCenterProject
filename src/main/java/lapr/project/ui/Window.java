package lapr.project.ui;

import com.toedter.calendar.JDateChooser;
import lapr.project.controller.*;
import lapr.project.model.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

import lapr.project.utils.Date.Date;

/**
 * Created by Mário Vaz on 10-Jun-17.
 */
public class Window extends JFrame {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 1L;

    /**
     * Window Dimension.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(1200, 600);
    /**
     * Event acceptance String
     */
    private static final String EVENT_ACCEPTANCE = "Event acceptance";
    /**
     * Global acceptance String
     */
    private static final String GLOBAL_ACCEPTANCE = "Global acceptance";
    /**
     * Test event acceptance rate is over 50% String
     */
    private static final String OVER_50 = "Test event acceptance rate is over 50%";
    /**
     * Compare Events acceptance String
     */
    private static final String COMPARE_EVENTS = "Compare Events acceptance";
    /**
     * FAE mean rating String
     */
    private static final String FAE_MEAN = "FAE mean rating";
    /**
     * Submission global mean rating String
     */
    private static final String SUBMISSION = "Submission global mean rating";
    /**
     * Mean deviation between FAE average ratings and global mean rating String
     */
    private static final String MEAN_DEVIATION = "Mean deviation between FAE average ratings and global mean rating";
    /**
     * Difference between the mean deviation and a theoretical value for a FAE
     * average String
     */
    private static final String DIFFERENCE = "Difference between the mean deviation and a theoretical value for a FAE average";
    /**
     * Compare FAEs average ratings String
     */
    private static final String COMPARE_FAE = "Compare FAEs average ratings";
    /**
     * Show stand information String
     */
    private static final String SHOW_STAND_INFO = "Show stand information";
    /**
     * Level 1 String
     */
    private static final String LEVEL_1 = "Level 1";
    /**
     * Level 5 String
     */
    private static final String LEVEL_5 = "Level 5";
    /**
     * Show Event Submission Keywords String
     */
    private static final String KEYWORDS = "Show Event Submission Keywords";
    /**
     * User Type: FAE
     */
    private static final String FAE = "FAE";
    /**
     * User Type: Organizer
     */
    private static final String ORGANIZER = "Organizer";
    /**
     * User Type: Event Manager
     */
    private static final String EVENT_MANAGER = "Event Manager";
    /**
     * User Type: Representative
     */
    private static final String REPRESENTATIVE = "Representative";
    /**
     * User Type: ADMIN
     */
    private static final String ADMIN = "ADMIN";
    /**
     * ADMIN name
     */
    private static final String ADMIN_NAME = "darkforce";
    /**
     * Approved String
     */
    private static final String APPROVED = "Approved!";
    /**
     * Disapproved String
     */
    private static final String DISAPPROVED = "Disapproved!";
    /**
     * No events to show String
     */
    private static final String NO_EVENTS_TO_SHOW = "No events to show";
    /**
     * Rate String
     */
    private static final String RATE = "Rate: ";
    /**
     * *.xml String
     */
    private static final String STAR_DOT_XML = "*.xml";
    /**
     * .xml String
     */
    private static final String DOT_XML = ".xml";
    /**
     * xml String
     */
    private static final String XML = "xml";
    /**
     * Error Message String
     */
    private static final String ERROR_MESSAGE = "Error";
    /**
     * Event Center
     */
    private EventCenter eventCenter;
    /**
     * Tabs with main program panels.
     */
    private JTabbedPane tabs;
    /**
     * MenuBar with main program rate UIs
     */
    private JMenuBar jMenuBar;

    /**
     * Test Current Date
     */
    private Date currentDate;

    /**
     * Import info controller
     */
    private ImportController importController;
    /**
     * Export info controller
     */
    private ExportController exportController;
    /**
     * Saved boolean
     */
    private boolean saved;

    /**
     * User type
     */
    private String userType;

    /**
     * Window class constructor
     *
     * @param eventCenter Event Center
     */
    public Window(EventCenter eventCenter, User currentUser) {
        super("Event Center");

        this.eventCenter = eventCenter;
        if (ADMIN_NAME.equals(currentUser.getUserName())) {
            userType = ADMIN;
        } else if (eventCenter.isEventManager(currentUser)) {
            userType = EVENT_MANAGER;
        } else if (eventCenter.isFAE(currentUser)) {
            userType = FAE;
        } else if (eventCenter.isOrganizer(currentUser)) {
            userType = ORGANIZER;
        } else {
            userType = REPRESENTATIVE;
        }

        this.saved = false;
        this.importController = new ImportController(this.eventCenter);
        this.exportController = new ExportController(this.eventCenter);

        createComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        setSize(WINDOW_DIMENSION);
        setMaximumSize(WINDOW_DIMENSION);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Calculate mean deviation message
     *
     * @param faeID      FAE user name
     * @param controller TestDifferenceMeanDeviationFaeController controller
     * @param difference Difference between FAE
     * @return mean deviation message
     */
    private static String calculateMeanDeviationMessage(String faeID, TestDifferenceMeanDeviationFaeController controller, double difference) {

        int count = controller.getCount();
        double faeMeanRating = controller.getFaeMeanRating();
        double deviationMean = controller.getDeviationMean();
        double standardDeviation = controller.getStandardDeviation();

        return "FAE: " + faeID + "\nNumber of submissions: " + count + "\nMean Rating: "
                + String.format("%.3f", faeMeanRating) + "\nDeviations' mean: " + String.format("%.3f", deviationMean)
                + "\nStandard deviation: " + String.format("%.3f", standardDeviation) + "\nObserved value of test statistic: " + String.format("%.3f", difference) + "\nDecision: ";
    }

    /**
     * Window for changing system current date.
     *
     * @param eventCenter Event Center
     */
    private JMenu testDate() {
        JMenu menu = new JMenu("Test");

        JMenuItem changeDate = new JMenuItem("Change Date");
        changeDate.addActionListener(al -> {
            JDateChooser date = new JDateChooser();
            JOptionPane.showMessageDialog(Window.this, date, "Event Current Date", JOptionPane.DEFAULT_OPTION);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String eventStartString = sdf.format(date.getDate().getTime());
            String[] dateSplit = eventStartString.split("/");
            currentDate = new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
            eventCenter.getEventRegister().updateEventsState(currentDate);
            updateTabs();
            updateMenu();
        });
        menu.add(changeDate);
        return menu;
    }

    /**
     * Create os components da window.
     */
    private void createComponents() {
        //MenuBar
        createMenuBar();

        //Separators
        tabs = new JTabbedPane();

        createSeparators();

    }

    /**
     * Create window MenuBar.
     */
    private void createMenuBar() {
        jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createRatesMenu());
        jMenuBar.add(testDate());
        setJMenuBar(jMenuBar);
    }

    /**
     * Create File Menu options.
     *
     * @return File Menu options.
     */
    private JMenu createFileMenu() {
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('f');
        menuFile.add(createImportItem());
        menuFile.add(createImportEventItem());
        menuFile.add(createExportItem());
        menuFile.addSeparator();
        menuFile.add(createCloseItem());

        return menuFile;
    }

    /**
     * Create menu item to import file.
     *
     * @return Import JMenuItem
     */
    private JMenuItem createImportItem() {
        JMenuItem importItem = new JMenuItem("Import", KeyEvent.VK_I);
        importItem.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        importItem.addActionListener(e -> importFile());
        return importItem;
    }

    /**
     * Create menu item to import event.
     *
     * @return Import event JMenuItem
     */
    private JMenuItem createImportEventItem() {
        JMenuItem importItem = new JMenuItem("Import Event");
        importItem.addActionListener(e -> importEventFile());
        return importItem;
    }

    /**
     * Create menu item to export eventCenter.
     *
     * @return Export JMenuItem
     */
    private JMenuItem createExportItem() {
        JMenuItem exportItem = new JMenuItem("Export", KeyEvent.VK_E);
        exportItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        exportItem.addActionListener(e -> exportFile());
        return exportItem;
    }

    /**
     * Create menu item to close window.
     *
     * @return Close JMenuItem
     */
    private JMenuItem createCloseItem() {
        JMenuItem close = new JMenuItem("Close", KeyEvent.VK_C);
        close.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        close.addActionListener(e -> closeWindow());
        return close;
    }

    /**
     * Create Rates Menu options.
     *
     * @return Rates Menu options.
     */
    private JMenu createRatesMenu() {
        JMenu menuFile = new JMenu("Rates");
        menuFile.setMnemonic('r');

        if (userType.equals(ADMIN) || userType.equals(ORGANIZER)) {
            menuFile.add(createEventKeywords());
            menuFile.add(createStandFrequencyTable());
        }
        if (userType.equals(EVENT_MANAGER) || userType.equals(ADMIN)) {
            menuFile.add(createEventAcceptanceItem());
            menuFile.add(createStandFrequencyTable());
            menuFile.add(createGlobalAcceptanceItem());
            menuFile.add(createCompareGlobalAndEventItem());
            menuFile.add(createCompareEventsItem());
            menuFile.add(createFAEItem());
            menuFile.add(createSubmissionItem());
            menuFile.add(createMeanDeviationItem());
            menuFile.add(createDifferenceItem());
            menuFile.add(createCompareFAEItem());
        }
        return menuFile;
    }

    /**
     * Create Event Keywords Menu Item.
     *
     * @return Event Keywords Menu Item.
     */
    private JMenuItem createEventKeywords() {
        JMenuItem menu = new JMenuItem(KEYWORDS);
        menu.addActionListener(a -> {
            EventKeywordsUI ui = new EventKeywordsUI(eventCenter);
            JOptionPane.showMessageDialog(Window.this, ui, KEYWORDS, JOptionPane.PLAIN_MESSAGE);
        });
        return menu;
    }

    /**
     * Create menu to Event acceptance.
     *
     * @return Event acceptance JMenu
     */
    private JMenu createEventAcceptanceItem() {
        JMenu jMenu = new JMenu(EVENT_ACCEPTANCE);
        ShowEventAcceptanceRateController controller = new ShowEventAcceptanceRateController(eventCenter);
        List<String> events = controller.getEvents();
        for (String eventID : events) {
            JMenuItem jMenuItem = new JMenuItem(eventID);
            jMenu.add(jMenuItem);
            jMenuItem.addActionListener(e -> {
                if (!NO_EVENTS_TO_SHOW.equals(eventID)) {
                    controller.setEvent(eventID);
                    String message = RATE + controller.calculateTheAcceptanceRate() + "%";
                    createRateDialog(message, EVENT_ACCEPTANCE);
                }
            });
        }
        return jMenu;
    }

    /**
     * Create menu item to Global acceptance.
     *
     * @return Global acceptance JMenuItem
     */
    private JMenuItem createGlobalAcceptanceItem() {
        JMenuItem jMenuItem = new JMenuItem(GLOBAL_ACCEPTANCE);
        ShowGlobalAcceptanceRateController controller = new ShowGlobalAcceptanceRateController(eventCenter);
        jMenuItem.addActionListener(e -> {
            String message = RATE + controller.calculateTheGlobalAcceptanceRate() + "%";
            createRateDialog(message, GLOBAL_ACCEPTANCE);
        });
        return jMenuItem;
    }

    /**
     * Create menu item to Compare Global and Event acceptance.
     *
     * @return Compare Global and Event acceptance JMenuItem
     */
    private JMenuItem createCompareGlobalAndEventItem() {
        JMenu jMenu = new JMenu(OVER_50);
        TestIfEventAcceptanceRateIsOverFiftyPercentController controller = new TestIfEventAcceptanceRateIsOverFiftyPercentController(eventCenter);
        List<String> events = controller.getEvents();
        for (String eventID : events) {
            JMenu jMenuEvent = new JMenu(eventID);
            jMenu.add(jMenuEvent);

            if (!NO_EVENTS_TO_SHOW.equals(eventID)) {

                controller.setEvent(eventID);

                JMenuItem jMenuItemLevel1 = new JMenuItem(LEVEL_1);
                jMenuEvent.add(jMenuItemLevel1);
                jMenuItemLevel1.addActionListener(e -> {
                    if (controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1()) {
                        createRateDialog(APPROVED, OVER_50);
                    } else {
                        createRateDialog(DISAPPROVED, OVER_50);
                    }
                });

                JMenuItem jMenuItemLevel5 = new JMenuItem(LEVEL_5);
                jMenuEvent.add(jMenuItemLevel5);
                jMenuItemLevel5.addActionListener(e -> {
                    if (controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5()) {
                        createRateDialog(APPROVED, OVER_50);
                    } else {
                        createRateDialog(DISAPPROVED, OVER_50);
                    }
                });
            }
        }
        return jMenu;
    }

    /**
     * Create menu to Compare Events acceptance.
     *
     * @return Compare Events acceptance JMenu
     */
    private JMenu createCompareEventsItem() {
        JMenu jMenu = new JMenu(COMPARE_EVENTS);

        JMenuItem jMenuItemLevel1 = new JMenuItem(LEVEL_1);
        jMenu.add(jMenuItemLevel1);
        jMenuItemLevel1.addActionListener(e -> new TestTheDifferenceBetweenTwoEventsAcceptanceRateUI(eventCenter, Window.this, LEVEL_1));

        JMenuItem jMenuItemLevel5 = new JMenuItem(LEVEL_5);
        jMenu.add(jMenuItemLevel5);
        jMenuItemLevel5.addActionListener(e -> new TestTheDifferenceBetweenTwoEventsAcceptanceRateUI(eventCenter, Window.this, LEVEL_5));
        return jMenu;
    }

    /**
     * Create menu to FAE mean rating.
     *
     * @return FAE mean rating JMenu
     */
    private JMenu createFAEItem() {
        JMenu jMenu = new JMenu(FAE_MEAN);
        ShowAFaeMeanRatingController controller = new ShowAFaeMeanRatingController(eventCenter);
        List<String> events = controller.getEvents();
        for (String eventID : events) {
            JMenu jMenuEvent = new JMenu(eventID);
            jMenu.add(jMenuEvent);

            if (!(NO_EVENTS_TO_SHOW.equals(eventID))) {
                controller.setEvent(eventID);

                List<String> faes = controller.getFaes();
                for (String faeID : faes) {
                    JMenuItem jMenuItemFae = new JMenuItem(faeID);
                    jMenuEvent.add(jMenuItemFae);

                    if (!("No FAEs to show".equals(faeID))) {

                        jMenuItemFae.addActionListener(e -> {
                            controller.setFAE(faeID, eventID);
                            String message = RATE + (Math.round(controller.calculateAFaeMeanRating() * 100.0) / 100.0);
                            createRateDialog(message, FAE_MEAN);
                        });
                    }
                }
            }
        }
        return jMenu;
    }

    /**
     * Create menu item to Submission global mean rating.
     *
     * @return Submission global mean rating JMenuItem
     */
    private JMenuItem createSubmissionItem() {
        JMenuItem jMenuItem = new JMenuItem(SUBMISSION);
        GlobalMeanRatingController controller = new GlobalMeanRatingController(eventCenter);
        jMenuItem.addActionListener(e -> {
            String message = RATE + controller.calcGlobalMeanRating() + "%";
            createRateDialog(message, SUBMISSION);
        });
        return jMenuItem;
    }

    /**
     * Create menu item to Mean deviation between FAE average ratings and global
     * mean rating.
     *
     * @return Mean deviation between FAE average ratings and global mean rating
     * JMenuItem
     */
    private JMenu createMeanDeviationItem() {
        JMenu jMenu = new JMenu(MEAN_DEVIATION);

        MeanDeviationFaeGlobalController controller = new MeanDeviationFaeGlobalController(eventCenter);

        for (String eventID : controller.getEvents()) {
            JMenuItem jMenuEvent = new JMenuItem(eventID);
            jMenu.add(jMenuEvent);

            if (!(NO_EVENTS_TO_SHOW.equals(eventID))) {

                jMenuEvent.addActionListener(a -> {

                    DefaultTableModel model = new DefaultTableModel();
                    JTable table = new JTable(model);
                    table.setEnabled(true);
                    JScrollPane scroll = new JScrollPane(table);
                    model.addColumn("FAE");
                    model.addColumn("Mean Deviation");
                    model.addColumn("Standard Deviation");
                    controller.setEvent(eventID);
                    String[][] info = controller.calcMeanDeviationFaeGlobal();
                    for (String[] info1 : info) {
                        model.addRow(info1);
                    }
                    JOptionPane.showMessageDialog(Window.this, scroll, MEAN_DEVIATION, JOptionPane.DEFAULT_OPTION);
                });
            }
        }
        return jMenu;

    }

    /**
     * Create menu to Difference between the mean deviation and a theoretical
     * value for a FAE average.
     *
     * @return Difference between the mean deviation and a theoretical value for
     * a FAE average JMenu
     */
    private JMenu createDifferenceItem() {
        JMenu jMenu = new JMenu(DIFFERENCE);
        TestDifferenceMeanDeviationFaeController controller = new TestDifferenceMeanDeviationFaeController(eventCenter);
        List<String> faes = controller.getFAEs();
        for (String faeID : faes) {
            JMenu jMenuFAE = new JMenu(faeID);
            jMenu.add(jMenuFAE);

            if (!"No FAEs to show".equals(faeID)) {

                JMenuItem jMenuItemLevel1 = new JMenuItem(LEVEL_1);
                jMenuFAE.add(jMenuItemLevel1);
                jMenuItemLevel1.addActionListener(e -> {
                    controller.setFAE(faeID);
                    double difference = controller.testDifferenceMeanDeviationFae();
                    String message = calculateMeanDeviationMessage(faeID, controller, difference);

                    createRateDialog(message + controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel1(difference), DIFFERENCE);
                });

                JMenuItem jMenuItemLevel5 = new JMenuItem(LEVEL_5);
                jMenuFAE.add(jMenuItemLevel5);
                jMenuItemLevel5.addActionListener(e -> {
                    controller.setFAE(faeID);
                    double difference = controller.testDifferenceMeanDeviationFae();
                    String message = calculateMeanDeviationMessage(faeID, controller, difference);

                    createRateDialog(message + controller.ensureThatTheHypothesisIsRejectedForTheSignificanceLevel5(difference), DIFFERENCE);
                });
            }
        }
        return jMenu;
    }

    /**
     * Create menu to Compare FAEs average ratings.
     *
     * @return Compare FAEs average ratings JMenu
     */
    private JMenu createCompareFAEItem() {
        JMenu jMenu = new JMenu(COMPARE_FAE);

        JMenuItem jMenuItemLevel1 = new JMenuItem(LEVEL_1);
        jMenu.add(jMenuItemLevel1);
        jMenuItemLevel1.addActionListener(e -> new TestDifferenceTwoFaeUI(LEVEL_1, eventCenter, Window.this));

        JMenuItem jMenuItemLevel5 = new JMenuItem(LEVEL_5);
        jMenu.add(jMenuItemLevel5);
        jMenuItemLevel5.addActionListener(e -> new TestDifferenceTwoFaeUI(LEVEL_5, eventCenter, Window.this));
        return jMenu;
    }

    /**
     * Create ShowMessageDialog to given rate
     */
    private void createRateDialog(String message, String rateName) {
        JOptionPane.showMessageDialog(Window.this, message, rateName, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Create separators for different functions.
     */
    private void createSeparators() {

        createTabs();

        add(tabs, BorderLayout.CENTER);

    }

    /**
     * Create Tabs that contain main panels
     */
    private void createTabs() {
        if (userType.equals(EVENT_MANAGER) || userType.equals(ADMIN)) {
            tabs.add("Create Event", createCreateEventPanel());
        }
        if (userType.equals(ADMIN) || userType.equals(ORGANIZER)) {
            tabs.add("Define FAE", createDefineFaePanel());
            tabs.add("Assign stand to Application", createAssignStandToApplicationPanel());
            tabs.add("Create Stand", createCreateStandPanel());
            tabs.add("List Applications", createListApplicationsPanel());
        }
        if (userType.equals(ADMIN) || userType.equals(FAE)) {
            tabs.add("Decide Application", createDecideApplicationPanel());
        }
        if (userType.equals(ADMIN) || userType.equals(REPRESENTATIVE)) {
            tabs.add("Submit Application", createSubmitApplicationPanel());
            tabs.add("Change or Remove Application", createChangeOrRemoveApplicationPanel());
        }
    }

    /**
     * Create Assign stand to Application panel.
     *
     * @return Assign stand to Application panel.
     */
    private JPanel createAssignStandToApplicationPanel() {
        return new AssignStandToApplicationUI(eventCenter, Window.this);
    }

    /**
     * Create Change or Remove Application panel.
     *
     * @return Change or Remove Application panel.
     */
    private JPanel createChangeOrRemoveApplicationPanel() {
        return new ChangeOrRemoveApplicationUI(eventCenter, Window.this);
    }

    /**
     * Create Create Event panel.
     *
     * @return Create Event panel.
     */
    private JPanel createCreateEventPanel() {
        return new CreateEventUI(eventCenter, Window.this);
    }

    /**
     * Create Create Stand panel.
     *
     * @return Create Stand panel.
     */
    private JPanel createCreateStandPanel() {
        return new CreateStandUI(eventCenter, Window.this);
    }

    /**
     * Create Decide Application panel.
     *
     * @return Decide Application panel.
     */
    private JPanel createDecideApplicationPanel() {
        return new DecideApplicationUI(eventCenter, new FAE(new User()), Window.this);
    }

    /**
     * Create Define FAE panel.
     *
     * @return Define FAE panel.
     */
    private JPanel createDefineFaePanel() {
        return new DefineFAEUI(eventCenter, Window.this);
    }

    /**
     * Create List Applications panel.
     *
     * @return List Applications panel.
     */
    private JPanel createListApplicationsPanel() {
        return new ListApplicationsUI(eventCenter);
    }

    /**
     * Create Submit Application panel.
     *
     * @return Submit Application panel.
     */
    private JPanel createSubmitApplicationPanel() {
        return new SubmitApplicationUI(eventCenter, new User(), Window.this);
    }

    /**
     * JMenuItem close function. Closes window.
     */
    private void closeWindow() {

        if (!saved) {
            UIManager.put("OptionPane.yesButtonText", "Yes");
            UIManager.put("OptionPane.noButtonText", "No");
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            int option = JOptionPane.showConfirmDialog(Window.this, "Save changes?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == 0) {
                if (exportFile()) {
                    this.dispose();
                }
            } else if (option == 1) {
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    /**
     * Import file action
     */
    private void importFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(STAR_DOT_XML, XML));
        fileChooser.setDialogTitle("Import file");
        if (fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getPath();

            if (!selectedFile.endsWith(DOT_XML)) {
                selectedFile += DOT_XML;
            }

            importController.setFile(selectedFile);
            String key = UiUtils.keyPopUP(this);

            if (!importController.importFile(key)) {
                JOptionPane.showMessageDialog(fileChooser, "File hasn't imported", ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
            updateTabs();
        }
    }

    /**
     * Export file action
     *
     * @return Logic value oos the success of the operation
     */
    private boolean exportFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export file");
        fileChooser.setFileFilter(new FileNameExtensionFilter(STAR_DOT_XML, XML));
        if (fileChooser.showSaveDialog(Window.this) == JFileChooser.APPROVE_OPTION) {

            String selectedFile = fileChooser.getSelectedFile().getPath();
            SavedFileManager save = new SavedFileManager();
            if (!selectedFile.endsWith(DOT_XML)) {
                selectedFile += DOT_XML;
                try {
                    File newFile = new File(selectedFile);
                    if (!newFile.createNewFile()) {
                        return false;
                    }
                    save.savedFilePath(newFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(fileChooser, "File hasn't Exported", ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);

                }
            } else {
                save.savedFilePath(fileChooser.getSelectedFile());
            }

            exportController.setFile(selectedFile);
            String key;
            do {
                key = UiUtils.keyPopUP(this);
            } while (key.length() <= 0);
            if (!exportController.exportFile(key)) {
                JOptionPane.showMessageDialog(fileChooser, "File hasn't Exported", ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            saved = true;

            return true;
        }
        return false;
    }

    /**
     * Import event file action
     */
    private void importEventFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(STAR_DOT_XML, XML));
        fileChooser.setDialogTitle("Import file");
        if (fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getPath();

            if (!selectedFile.endsWith(DOT_XML)) {
                selectedFile += DOT_XML;
            }

            new ImportEventDialog(this, "Selected Organizers", true, eventCenter, selectedFile);

            updateTabs();
            updateMenu();
        }
    }

    /**
     * Update tabs that contain main panels
     */
    public void updateTabs() {
        int actualTab = tabs.getSelectedIndex();
        tabs.removeAll();
        createSeparators();
        this.validate();
        tabs.setSelectedIndex(actualTab);
    }

    /**
     * Update JMenu that contain main rate UIs
     */
    public void updateMenu() {
        jMenuBar.removeAll();
        createMenuBar();
        this.validate();
    }

    /**
     * Create menu to Show stand information.
     *
     * @return Show stand information JMenu
     */
    private JMenu createStandFrequencyTable() {
        JMenu jMenu = new JMenu(SHOW_STAND_INFO);
        ShowStandInfoController controller = new ShowStandInfoController(eventCenter);
        List<String> events = controller.getEvents();
        for (String eventID : events) {
            JMenuItem jMenuEvent = new JMenuItem(eventID);
            jMenu.add(jMenuEvent);

            if (!(NO_EVENTS_TO_SHOW.equals(eventID))) {
                jMenuEvent.addActionListener(e -> JOptionPane.showMessageDialog(Window.this, new StandInfoUI(controller, eventID), SHOW_STAND_INFO, JOptionPane.PLAIN_MESSAGE));

            }

        }
        return jMenu;

    }
}
