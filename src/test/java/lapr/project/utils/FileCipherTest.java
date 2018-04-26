/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author U
 */
public class FileCipherTest {

    @Test
    public void testChipher() {
        String expected = "Teste_. @-_;";
        FileCipher a = new FileCipher("key");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }

    @Test
    public void testChipherLongString() {
        String expected = "askfçslfksdoqriepwtfpodsofvmsdpipretierpif";
        FileCipher a = new FileCipher("key12");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }

    @Test
    public void testChipherLengthDivision() {
        String expected = "abcbnn1";
        FileCipher a = new FileCipher("key12");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }

    @Test
    public void testChipherWithOneLetterKey() {
        String expected = "Teste_. @-_;";
        FileCipher a = new FileCipher("k");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }


    @Test
    public void testChipherWithKeyWithNumbers() {
        String expected = "Teste_. @-_;";
        FileCipher a = new FileCipher("1bc1334");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }

    @Test
    public void testChipherWithKeySpecialChar() {
        String expected = "Teste_. @-_;";
        FileCipher a = new FileCipher("/@_.");

        String result = a.cipher(expected);

        assertEquals(expected, a.decipher(result).trim());
    }

    @Test
    public void testChipherNullData() {
        String nullString = null;
        FileCipher a = new FileCipher("/@_.");

        String result = a.cipher(nullString);

        String expected = "";
        assertEquals(expected, result);
        assertEquals(expected, a.decipher(result).trim());
    }

}
