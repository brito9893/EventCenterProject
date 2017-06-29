/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lapr.project.model.Application;
import lapr.project.model.Decision;
import lapr.project.model.FAE;
import lapr.project.model.Keyword;
import lapr.project.model.Representative;
import lapr.project.model.Stand;
import lapr.project.model.User;
import lapr.project.model.registers.DecisionList;
import lapr.project.model.registers.KeywordList;
import lapr.project.model.registers.StandList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author U
 */
public class XMLReaderTest {

    @Test
    public void testXMLReaderGetEventName() throws Exception {
        XMLReader reader = new XMLReader(new File("src/test/resources/expo_teste.xml"));
        String expected = "expoTest";
        String result = reader.getEventTitle();
        assertEquals(expected, result);
    }

    @Test(expected = Exception.class)
    public void testXMLReaderNotHavingMainTag() throws Exception {
        XMLReader reader = new XMLReader(new File("src/test/resources/test11.xml"));

    }

    @Test
    public void testXMLReaderGetListStand() throws Exception {
        XMLReader reader = new XMLReader(new File("src/test/resources/expo_teste.xml"));
        StandList tempStandList = new StandList();
        List<Stand> expected = new ArrayList<>();
        Stand st1 = tempStandList.newStand(37, "Stand 1");
        Stand st2 = tempStandList.newStand(103, "Stand 2");
        List<Stand> result = reader.getStandList();
        assertEquals(result.get(0), st1);
        assertEquals(result.get(1), st2);
    }

    @Test
    public void testXMLReaderGetListFAE() throws Exception {
        XMLReader reader = new XMLReader(new File("src/test/resources/expo_teste.xml"));

        List<FAE> expected = new ArrayList<>();
        expected.add(new FAE(new User("Fae1", "fae1@centro.pt", "fae1@centro.pt", "password")));
        expected.add(new FAE(new User("Fae2", "fae2@centro.pt", "fae2@centro.pt", "password")));
        List<FAE> result = reader.getFaeList();
        assertTrue(expected.containsAll(result));
    }

    @Test
    public void testXMLReaderGetListApplications() throws Exception {
        XMLReader reader = new XMLReader(new File("src/test/resources/expo_teste.xml"));


        Representative rep = new Representative(new User("Desconhecido", "Desconhecido", "Desconhecido", "Desconhecido"));
        KeywordList keywords = new KeywordList();
        keywords.addKeyword(new Keyword("Crabby"));
        keywords.addKeyword(new Keyword("Memory"));
        keywords.addKeyword(new Keyword("Attract"));
        keywords.addKeyword(new Keyword("Natural"));

        Application app = new Application(rep, "Application1", "Descrição: 15886", 86, 158, keywords);
        FAE fae1 = new FAE(new User("Fae1", "fae1@centro.pt", "fae1@centro.pt", "password"));
        FAE fae2 = new FAE(new User("Fae2", "fae2@centro.pt", "fae2@centro.pt", "password"));
        DecisionList dl = app.getDecisionList();
        List<Integer> rate1 = new ArrayList<>();
        rate1.add(0);
        rate1.add(1);
        rate1.add(5);
        rate1.add(3);
        List<Integer> rate2 = new ArrayList<>();
        rate2.add(5);
        rate2.add(1);
        rate2.add(0);
        rate2.add(3);
        Decision d1 = new Decision(rate1, true, "texto0153", fae1);
        Decision d2 = new Decision(rate2, true, "texto5103", fae2);
        dl.addDecision(d1);
        dl.addDecision(d2);

        List<Application> result = reader.getApplicationsRegister();
        int expectedSize = 1;
        int expectedKeywords = 4;
        Application appResult = result.get(0);

        assertEquals(appResult.getDecisionList(), dl);
        assertEquals(expectedKeywords, appResult.getKeywordList().getSize());
        assertEquals(expectedSize, result.size());
        assertEquals(app, result.get(0));
    }

}
