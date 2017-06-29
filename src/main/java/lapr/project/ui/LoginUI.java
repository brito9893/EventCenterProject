/*
 * To change jFrame license header, choose License Headers in Project Properties.
 * To change jFrame template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.model.*;
import lapr.project.utils.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Login User Interface
 *
 * @author Luís Cunha
 */
class LoginUI {

    /**
     * Error Message String
     */
    private static final String ERROR_MESSAGE = "Error";
    /**
     * Login String
     */
    private static final String LOGIN = "Login";
    /**
     * Main Frame
     */
    private final JFrame jFrame;
    /**
     * Event Center
     */
    private final EventCenter center;
    /**
     * Username TextField
     */
    private JTextField userNameField;
    /**
     * Password Field
     */
    private JPasswordField passwordField;
    /**
     * Loggable boolean
     */
    private boolean loggable;

    /**
     * Class constructor
     *
     * @param center Event Center
     */
    public LoginUI(EventCenter center) {
        this.center = center;
        this.jFrame = new JFrame();
        initWindow();
        loggable = false;
        fileLaunch();

    }

    /**
     * Create window
     */
    private void initWindow() {
        String defaultTitle = "LOGIN";
        jFrame.setTitle(defaultTitle);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();
        jFrame.pack();
        jFrame.getContentPane().requestFocusInWindow();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    /**
     * Create Components
     */
    private void createComponents() {
        GridLayout layout = new GridLayout(5, 0);
        jFrame.setLayout(layout);
        jFrame.add(Box.createVerticalStrut(10));
        jFrame.add(createUserNamePanel());
        jFrame.add(createPasswordPanel());
        jFrame.add(createButtons());
        jFrame.add(Box.createVerticalStrut(10));
    }

    /**
     * Create username Panel
     *
     * @return username Panel
     */
    private JPanel createUserNamePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Username: ");
        userNameField = new JTextField(15);

        panel.add(Box.createHorizontalStrut(10));
        panel.add(label);
        panel.add(userNameField);
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    /**
     * Create password Panel
     *
     * @return password Panel
     */
    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Password: ");
        passwordField = new JPasswordField(15);

        panel.add(Box.createHorizontalStrut(10));
        panel.add(label);
        panel.add(passwordField);
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    /**
     * Create buttons Panel
     *
     * @return buttons Panel
     */
    private JPanel createButtons() {
        JPanel panel = new JPanel();

        JButton login = new JButton(LOGIN);
        login.setToolTipText("Click here to Login in.");
        login.addActionListener(this::loginEvent);
        jFrame.getRootPane().setDefaultButton(login);

        JButton registeUser = new JButton("Create Account");
        registeUser.setToolTipText("Click here to register a new account");
        registeUser.addActionListener(this::eventRegister);

        panel.add(Box.createHorizontalStrut(10));
        panel.add(login);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(registeUser);
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    /**
     * Login Event
     *
     * @param e event
     */
    @SuppressWarnings("unused")
    private void loginEvent(ActionEvent e) {
        User user = center.getUserRegister().getUserByUserName(userNameField.getText().trim());
        if (user != null) {
            if (user.isPasswordCorrect(new String(passwordField.getPassword()))) {
                if (loggable) {
                    new Window(center, user);
                    jFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(jFrame, "Wait for Infomation to be read.", LOGIN, JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, " password.", LOGIN, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(jFrame, "Wrong username", LOGIN, JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Event Register
     *
     * @param e event
     */
    @SuppressWarnings("unused")
    private void eventRegister(ActionEvent e) {
        new RegisterUserDialog(jFrame, true, center);
    }

    /**
     * Import Start info
     *
     * @param center Event Center
     */
    private void importStartInfo(EventCenter center) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
            int returnVal = fileChooser.showDialog(jFrame, "Import");

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                String key = UiUtils.keyPopUP(jFrame);

                XMLMarshaller.importEventCenterData(center, fileChooser.getSelectedFile(), key);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(jFrame, "The file can't be read.", ERROR_MESSAGE, 0);

        }
    }

    /**
     * File Launch
     */
    private void fileLaunch() {
        try {

            SavedFileManager save = new SavedFileManager();
            String path = save.importFilePath("EventCenter.tmp");
            if (path == null) {
                throw new FileNotFoundException();
            }
            File file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            String key = UiUtils.keyPopUP(jFrame);
            if (key.length() != 0) {
                XMLMarshaller.importEventCenterData(center, file, key);
            }
            loggable = true;
        } catch (FileNotFoundException e) {
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(jFrame, "Missing EventCenter Information File \nDo you want to choose other import file ?", ERROR_MESSAGE, 0)) {
                importStartInfo(center);
            }
            loggable = true;
        } catch (Exception e) {
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(jFrame, "There is a probleam with Event Center information file.\nDo you want to choose other import file ?", ERROR_MESSAGE, 0)) {
                importStartInfo(center);
            }
            loggable = true;
        }
    }

}
