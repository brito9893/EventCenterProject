/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Date.Date;
import org.junit.*;

import java.util.*;

/**
 * @author U
 */
public class ExhibitionTest {

    @Test
    public void testExhibitionConstructer() {

        Date d1 = new Date(2017, 6, 9);
        Date d2 = new Date(2017, 6, 10);
        Date d3 = new Date(2017, 1, 1);
        Date d4 = new Date(2017, 6, 1);


        List<User> user = new ArrayList<>();
        user.add(new User("user2", "userName2", "user2@email.com", "password2", User.Language.ENGLISH));
        Exhibition event = new Exhibition("event", "description2", d1, d2, d3, d4, user);

        Exhibition event2 = new Exhibition();
    }

}
