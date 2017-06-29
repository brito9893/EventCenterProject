/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * @author U
 */
public class StatisticsTest {

    @Test
    public void testeListLessThatCounter() {
        ArrayList<Double> list = new ArrayList<>();
        List<Double> out = Statistics.getRandom(list, 30);
        list.add(1.0);
        int expected = 1;
        int result = out.size();
        assertEquals(expected, result);

    }

    @Test
    public void testeListSampleCounter() {
        ArrayList<Double> list = new ArrayList<>();
        for (int a = 0; a < 50; a++) {
            list.add(Math.random());
        }
        List<Double> out = Statistics.getRandom(list, 30);
        int expected = 30;
        int result = out.size();
        assertEquals(expected, result);

    }

    @Test
    public void testeSampleIsDifferentCounter() {
        ArrayList<Double> list = new ArrayList<>();
        for (int a = 0; a < 50; a++) {
            list.add(Math.random());
        }
        List<Double> out = Statistics.getRandom(list, 30);
        List<Double> out2 = Statistics.getRandom(list, 30);

        assertFalse(out.containsAll(out2));

    }

}
