/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author U
 */
public final class UiUtils {

    /**
     * Pop window to keyword introduction
     *
     * @param parent parent window
     * @return keyword
     */
    static String keyPopUP(Component parent) {

        JPanel p = new JPanel();
        JTextField keyword = new JTextField(7);
        JLabel label = new JLabel("Keyword:");

        p.add(label);
        p.add(keyword);
        JOptionPane.showMessageDialog(parent, p, "Keyword", JOptionPane.PLAIN_MESSAGE);

        return keyword.getText();
    }
}
