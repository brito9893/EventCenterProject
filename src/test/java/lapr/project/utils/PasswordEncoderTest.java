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
public class PasswordEncoderTest {

    @Test
    public void ensuresPasswordEncoderIsCorrect() {
        String password = "Luis_123;";
        String expected = "Pymwc567?";

        PasswordEncoder result = new PasswordEncoder(password);
        PasswordEncoder result2 = new PasswordEncoder(password);


        assertEquals(expected, result.toString());
        assertEquals(expected, result2.toString());

    }

    @Test
    public void ensuresPasswordEncoderIsAlwaysTheSame() {
        String password = "Luis_123;";
        String expected = "Pymwc567?";

        PasswordEncoder result = new PasswordEncoder(password);
        PasswordEncoder result2 = new PasswordEncoder(password);


        assertEquals(result.toString(), result2.toString());
        assertEquals(expected, result.toString());
        assertEquals(expected, result2.toString());

    }

    @Test
    public void ensuresPasswordEncoderVogalsAreWorking() {
        String password = "aeio";
        String expected = "gkou";
        PasswordEncoder result = new PasswordEncoder(password);
        assertEquals(expected, result.toString());
        password = "AEIU";
        expected = "GKO[";
        result = new PasswordEncoder(password);
        assertEquals(expected, result.toString());
    }


    @Test
    public void ensuresPasswordEncoderVogalisNull() {
        String password = null;
        String expected = null;
        PasswordEncoder result = new PasswordEncoder(password);
        assertEquals(expected, result.toString());
    }


}
