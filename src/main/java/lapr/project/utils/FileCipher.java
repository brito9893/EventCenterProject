/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Luís Cunha
 */
@XmlRootElement
@Entity
public class FileCipher {

    private static final String EMPTY = "";
    private static final int ASCII_START = 33;
    private static final int ASCII_END = 126;
    private static final Logger LOGGER = Logger.getLogger(FileCipher.class.getName());
    @XmlElement
    private String keyword;
    private String id;

    public FileCipher(String keyword) {
        this.keyword = keyword;
    }

    public FileCipher() {
    }

    public String cipher(String data) {

        StringBuilder bd = new StringBuilder();
        try (Scanner scan = new Scanner(data)) {
            while (scan.hasNextLine()) {
                String next = scan.nextLine();
                String sub = substituionChiper(next);
                String trans = transpositionCipher(sub);
                bd.append(trans);
                bd.append("\n");
            }
            return bd.toString();

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.toString());
            return EMPTY;
        }

    }

    public String decipher(String data) {

        StringBuilder bd = new StringBuilder();
        try (Scanner scan = new Scanner(data)) {
            while (scan.hasNextLine()) {
                String next = scan.nextLine();
                String trans = transpositionDecipher(next);
                String sub = substitutionDeCipher(trans);
                bd.append(sub);
                bd.append("\n");
            }
            return bd.toString();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.toString());
            return EMPTY;
        }
    }

    private String transpositionCipher(String plainText) {
        int depth = keyword.length();
        int len = plainText.length();
        int c = len / depth;
        int t = len % depth;
        char[][] mat = new char[depth][c];
        int k = 0;

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < depth; j++) {
                if (k != len) {
                    mat[j][i] = plainText.charAt(k++);
                } else {
                    mat[j][i] = '*';
                }
            }
        }
        StringBuilder bd = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < c; j++) {
                bd.append(mat[i][j]);
            }
        }
        if (t > 0) {
            for (int b = 1; b <= t; b++) {
                bd.append(plainText.charAt(len - b));
            }
        }
        return bd.toString();
    }

    private String transpositionDecipher(String cipherText) {
        int depth = keyword.length();
        int len = cipherText.length();
        int t = len % depth;
        int c = len / depth;
        char[][] mat = new char[depth][c];
        int k = 0;

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cipherText.charAt(k++);
            }
        }
        StringBuilder bd = new StringBuilder();
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < depth; j++) {
                bd.append(mat[j][i]);
            }
        }
        if (t > 0) {
            for (int b = 1; b <= t; b++) {
                bd.append(cipherText.charAt(len - b));
            }
        }
        return bd.toString();
    }

    private String substituionChiper(String text) {

        List<Character> ascii = new ArrayList<>();
        List<Character> encoded = new ArrayList<>();
        char[] keyChar = keyword.toCharArray();
        for (int ac = ASCII_START; ac <= ASCII_END; ac++) {
            ascii.add((char) ac);
        }
        for (char aKeyChar : keyChar) {
            encoded.add(aKeyChar);
        }
        for (int b = ASCII_START; b <= ASCII_END; b++) {
            if (!encoded.contains((char) b)) {
                encoded.add((char) b);
            }
        }

        char[] textArray = text.toCharArray();
        char[] encrypted = new char[textArray.length];

        for (int ctext = 0; ctext < textArray.length; ctext++) {
            if (textArray[ctext] == ' ') {
                encrypted[ctext] = ' ';
                continue;
            }

            int pos = ascii.indexOf(textArray[ctext]);
            if (pos != -1) {
                encrypted[ctext] = encoded.get(pos);
            } else {
                encrypted[ctext] = textArray[ctext];
            }

        }

        return new String(encrypted);

    }

    private String substitutionDeCipher(String text) {

        List<Character> ascii = new ArrayList<>();
        List<Character> encoded = new ArrayList<>();
        char[] keyChar = keyword.toCharArray();
        for (int ac = ASCII_START; ac <= ASCII_END; ac++) {
            ascii.add((char) ac);
        }
        for (int a = 0; a < keyChar.length; a++) {
            encoded.add(keyChar[a]);
        }
        for (int b = ASCII_START; b <= ASCII_END; b++) {
            if (!encoded.contains((char) b)) {
                encoded.add((char) b);
            }
        }

        char[] textArray = text.toCharArray();
        char[] decrypted = new char[textArray.length];

        int ctext = 0;
        while (ctext < textArray.length) {
            if (textArray[ctext] == ' ') {
                decrypted[ctext] = ' ';
                ctext++;
                continue;
            }
            int pos = encoded.indexOf(textArray[ctext]);
            if (pos != -1) {
                decrypted[ctext] = ascii.get(pos);
            } else {
                decrypted[ctext] = textArray[ctext];
            }
            ctext++;
        }
        return new String(decrypted);
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
