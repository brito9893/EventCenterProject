/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import lapr.project.controller.UserRegisterController;
import lapr.project.model.EventCenter;
import lapr.project.model.User;

/**
 * @author Luis Cunha on 06/06/2017
 */
public class RegisterUserDialog extends JDialog {

    /**
     * Serial Version UID
     */
    public static final long serialVersionUID = 1L;

    /**
     * User register controller
     */
    private final transient UserRegisterController controller;

    /**
     * Combo Box Model
     */
    private final transient DefaultComboBoxModel<String> comboBoxModel;

    /**
     * Confirm Button
     */
    private JButton confirmButton;

    /**
     * Email Label
     */
    private JLabel emailLabel;

    /**
     * Email TextField
     */
    private JTextField emailTextField;

    /**
     * Error Label
     */
    private JLabel errorLabel;

    /**
     * Language Combo Box
     */
    private JComboBox<String> languageComboBox;

    /**
     * Name TextField
     */
    private JTextField nameField;

    /**
     * Name Label
     */
    private JLabel nameLabel;

    /**
     * Password confirm Field
     */
    private JPasswordField passwordConfirmField;

    /**
     * Password Field
     */
    private JPasswordField passwordField;

    /**
     * Password Label
     */
    private JLabel passwordLabel;

    /**
     * Username TextField
     */
    private JTextField userNameTextField;

    /**
     * Username Label
     */
    private JLabel usernameLabel;
    private String charPass;

    /**
     * Creates new form RegisterUserDialog
     *
     * @param parent : Parent Frame
     * @param modal  : Is modal
     * @param center : Event Cente
     */
    RegisterUserDialog(java.awt.Frame parent, boolean modal, EventCenter center) {
        super(parent, modal);
        this.controller = new UserRegisterController(center);
        comboBoxModel = new DefaultComboBoxModel<>();
        addLanguagesToModel();
        initComponents();
        mouseListener();
        this.setVisible(true);
        charPass = "Passwords must have 8 characters.";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameField = new JTextField();
        JLabel nameFieldLabel = new JLabel();
        JLabel emailFieldLabel = new JLabel();
        emailTextField = new JTextField();
        JLabel usernameFieldLabel = new JLabel();
        userNameTextField = new JTextField();
        JLabel passwordFieldLabel = new JLabel();
        JLabel retypeFieldLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordConfirmField = new JPasswordField();
        confirmButton = new JButton();
        JButton cancelButton = new JButton();
        JLabel languageFieldLabel = new JLabel();
        languageComboBox = new JComboBox<>();
        JLabel jLabel7 = new JLabel();
        errorLabel = new JLabel();
        nameLabel = new JLabel();
        emailLabel = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account Regist");
        setResizable(false);

        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFieldFocusLost(evt);
            }
        });

        nameFieldLabel.setText("Name:");

        emailFieldLabel.setText("Email :");

        emailTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusLost(evt);
            }
        });

        usernameFieldLabel.setText("Username:");

        userNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                userNameTextFieldFocusLost(evt);
            }
        });

        passwordFieldLabel.setText("Password:");

        retypeFieldLabel.setText("Retype Password :");

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });

        passwordConfirmField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordConfirmFieldFocusLost(evt);
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(this::confirmButtonActionPerformed);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        languageFieldLabel.setText("Language:");

        languageComboBox.setModel(comboBoxModel);

        String dialog = "Dialog";
        nameLabel.setFont(new java.awt.Font(dialog, Font.PLAIN, 10)); // NOI18N

        emailLabel.setFont(new java.awt.Font(dialog, Font.PLAIN, 10)); // NOI18N

        usernameLabel.setFont(new java.awt.Font(dialog, Font.PLAIN, 10)); // NOI18N

        passwordLabel.setText(charPass);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(25, 25, 25)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(nameFieldLabel)
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                                .addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(emailFieldLabel)
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(emailTextField)
                                                                                                .addComponent(emailLabel, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(passwordFieldLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(usernameFieldLabel)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(languageFieldLabel)
                                                                                        .addComponent(retypeFieldLabel))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(passwordConfirmField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(languageComboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(1, 1, 1))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(34, 34, 34)
                                                                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24, 24, 24)))
                                                .addGap(14, 14, 14))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(117, 117, 117)
                                                .addComponent(jLabel7))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameFieldLabel))
                                                .addGap(3, 3, 3)
                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emailFieldLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(usernameFieldLabel)
                                                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(passwordFieldLabel)
                                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(retypeFieldLabel)
                                                        .addComponent(passwordConfirmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(languageFieldLabel)
                                                        .addComponent(languageComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(confirmButton)
                                                        .addComponent(cancelButton))))
                                .addGap(0, 23, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(null);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Confirm Button Action Performed
     *
     * @param evt event
     */
    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        String name = nameField.getText().trim();
        String username = userNameTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        String language = (String) languageComboBox.getSelectedItem();

        controller.createUser(name, username, email, password, language);
        dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    /**
     * Email TextField focus lost
     *
     * @param evt event
     */
    private void emailTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusLost
        if (!controller.verifyEmail(emailTextField.getText().trim())) {
            emailLabel.setText("Email is already being used.");
        } else if (!(emailTextField.getText().contains("@") && emailTextField.getText().contains("."))) {
            emailLabel.setText("Invalid email format.");
        } else {
            emailLabel.setText("");
        }
        confirmButton.setEnabled(activateConfirmButton());
    }//GEN-LAST:event_emailTextFieldFocusLost

    /**
     * Username TextField focus lost
     *
     * @param evt event
     */
    private void userNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameTextFieldFocusLost
        if (!controller.verifyUserName(userNameTextField.getText().trim())) {
            usernameLabel.setText(errorLabel.getText() + "\nUsername is already being used.");
        } else {
            usernameLabel.setText("");
        }
        confirmButton.setEnabled(activateConfirmButton());
    }//GEN-LAST:event_userNameTextFieldFocusLost

    /**
     * Name Field focus lost
     *
     * @param evt event
     */
    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusLost
        confirmButton.setEnabled(activateConfirmButton());
    }//GEN-LAST:event_nameFieldFocusLost

    /**
     * Password confirm Field focus lost
     *
     * @param evt event
     */
    private void passwordConfirmFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordConfirmFieldFocusLost
        String passwordFieldString = new String(passwordField.getPassword());
        String passwordConfirmFieldString = new String(passwordConfirmField.getPassword());

        if (passwordConfirmFieldString.length() < 8) {
            passwordLabel.setText(charPass);
        } else if (!(passwordFieldString.equals(passwordConfirmFieldString)) && passwordFieldString.length() > 0) {
            passwordLabel.setText("Passwords not matching.");
        } else {
            passwordLabel.setText("");
        }
        confirmButton.setEnabled(activateConfirmButton());
    }//GEN-LAST:event_passwordConfirmFieldFocusLost

    /**
     * Password Field focus lost
     *
     * @param evt event
     */
    private void passwordFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        String passwordFieldString = new String(passwordField.getPassword());
        String passwordConfirmFieldString = new String(passwordConfirmField.getPassword());

        if (passwordFieldString.length() < 8) {
            passwordLabel.setText(charPass);
        } else if (!(passwordFieldString.equals(passwordConfirmFieldString)) && passwordConfirmFieldString.length() > 0) {
            passwordLabel.setText("Passwords not matching.");
        } else {
            passwordLabel.setText("");
        }
        confirmButton.setEnabled(activateConfirmButton());
    }//GEN-LAST:event_passwordFieldFocusLost

    /**
     * Cancel Button action performed
     *
     * @param evt event
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Add Languages to model
     */
    private void addLanguagesToModel() {
        for (User.Language lg : User.Language.values()) {
            comboBoxModel.addElement(lg.toString());
        }
    }

    /**
     * Mouse Listener
     */
    private void mouseListener() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                //do nothing
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                confirmButton.setEnabled(activateConfirmButton());
            }
        });
    }

    /**
     * Activate confirm button
     *
     * @return logic value of the success of the operation
     */
    private boolean activateConfirmButton() {
        String passwordFieldString = new String(passwordField.getPassword());
        String passwordConfirmFieldString = new String(passwordConfirmField.getPassword());

        return (nameField.getText().length() > 0 && nameLabel.getText().length() == 0) && (emailTextField.getText().length() > 0 && emailLabel.getText().length() == 0) && (userNameTextField.getText().length() > 0 && usernameLabel.getText().length() == 0) && passwordFieldString.equals(passwordConfirmFieldString) && passwordFieldString.length() >= 8;
    }
    // End of variables declaration                   
}
