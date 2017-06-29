/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 * Password Cipher
 *
 * @author Luís Cunha 04/06/2017
 */
public class PasswordEncoder {
    /**
     * Encoded Password
     */
    private final String passwordEncoded;

    /**
     * Password Encoder Constructor
     *
     * @param password Password to be encoded.
     */
    public PasswordEncoder(String password) {
        this.passwordEncoded = encodePassword(encodePassword(password));
    }

    /**
     * Encode Methoad
     *
     * @param password Password to be encoded.
     * @return Encoded Password.
     */
    private static String encodePassword(String password) {
        if (password == null) {
            return null;
        }

        int nCode = numberOfVogals(password);

        char[] passwordArray = password.toCharArray();

        for (int a = 0; a < password.length(); a++) {

            int charInt = (int) passwordArray[a];

            int charEncoded = charInt + nCode;

            if (charEncoded > 122) {
                charEncoded = 48;
            }

            passwordArray[a] = (char) charEncoded;

        }

        return new String(passwordArray);
    }

    /**
     * Method to count the number of vowls in the password.
     *
     * @param password Password.
     * @return number of vowls in that password.
     */
    private static int numberOfVogals(String password) {
        char[] passwordArray = password.toCharArray();
        int v = 0;
        for (char c : passwordArray) {
            if ("aeiou".indexOf(c) >= 0 || "AEIOU".indexOf(c) >= 0) {
                v++;
            }
        }

        return (v == 0) ? 5 : v;
    }

    /**
     * Encoded Password toString
     *
     * @return Encoded Password.
     */
    @Override
    public String toString() {
        return passwordEncoded;
    }
}
