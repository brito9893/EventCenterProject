/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.*;
import lapr.project.model.registers.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

/**
 * @author Luís Cunha on 13/06/2017
 */
public class XMLReader {

    private File file;
    private String eventTitle;
    private List<FAE> faeList;
    private List<Stand> standList;
    private List<Application> applicationsRegister;


    public XMLReader(File file) throws Exception {
        this.file = file;
        importEventDataFormXMLFile();
    }

    private static List<Stand> readStandList(NodeList standList) {
        List<Stand> modelStands = new ArrayList<>();
        if (standList.getLength() != 0) {
            NodeList stands = ((Element) standList.item(0)).getElementsByTagName("stand");

            StandList tempStandList = new StandList();
            for (int a = 0; a < stands.getLength(); a++) {

                Node standNode = stands.item(a);

                Element standElement = (Element) standNode;

                String description = standElement.getElementsByTagName("description").item(0).getTextContent();
                int area = Integer.parseInt(standElement.getElementsByTagName("area").item(0).getTextContent());

                modelStands.add(tempStandList.newStand(area, description));
            }
        }
        return modelStands;

    }

    private static List<FAE> readFaeSet(NodeList faeSetNode) {
        List<FAE> faes = new ArrayList<>();

        NodeList stands = ((Element) faeSetNode.item(0)).getElementsByTagName("fae");

        for (int a = 0; a < stands.getLength(); a++) {

            Node standNode = stands.item(a);

            Element faeElement = (Element) standNode;

            Node user = faeElement.getFirstChild().getNextSibling();

            Element userElement = (Element) user;

            String name = userElement.getElementsByTagName("name").item(0).getTextContent();
            String email = userElement.getElementsByTagName("email").item(0).getTextContent();
            String username = userElement.getElementsByTagName("username").item(0).getTextContent();
            String password = userElement.getElementsByTagName("password").item(0).getTextContent();
            faes.add(new FAE(new User(name, username, email, password)));
        }

        return faes;
    }

    private static List<Application> readApplicationSet(NodeList applicationSet) {
        List<Application> applicationsList = new ArrayList<>();

        NodeList applications = ((Element) applicationSet.item(0)).getElementsByTagName("application");
        int appN = 1;
        for (int a = 0; a < applications.getLength(); a++) {

            Element app = (Element) applications.item(a);

            boolean accpetance = Boolean.parseBoolean(app.getElementsByTagName("accepted").item(0).getTextContent());
            String description = app.getElementsByTagName("description").item(0).getTextContent();
            int area = Integer.parseInt(app.getElementsByTagName("boothArea").item(0).getTextContent());
            int invites = Integer.parseInt(app.getElementsByTagName("invitesQuantity").item(0).getTextContent());

            Node reviewsNode = app.getElementsByTagName("reviews").item(0);

            Element reviewsElm = (Element) reviewsNode;

            NodeList reviewlist = reviewsElm.getElementsByTagName("review");

            List<Decision> decisions = new ArrayList<>();

            for (int b = 0; b < reviewlist.getLength(); b++) {

                Element review = (Element) reviewlist.item(b);
                List<Integer> rates = new ArrayList<>();

                String reviewText = review.getElementsByTagName("text").item(0).getTextContent();
                rates.add(Integer.parseInt(review.getElementsByTagName("faeTopicKnowledge").item(0).getTextContent()));
                rates.add(Integer.parseInt(review.getElementsByTagName("eventAdequacy").item(0).getTextContent()));
                rates.add(Integer.parseInt(review.getElementsByTagName("inviteAdequacy").item(0).getTextContent()));
                rates.add(Integer.parseInt(review.getElementsByTagName("recommendation").item(0).getTextContent()));


                Element assig = (Element) review.getElementsByTagName("assignment").item(0);
                Element fae = (Element) assig.getElementsByTagName("fae").item(0);
                Element user = (Element) fae.getElementsByTagName("user").item(0);

                String name = user.getElementsByTagName("name").item(0).getTextContent();
                String email = user.getElementsByTagName("email").item(0).getTextContent();
                String username = user.getElementsByTagName("username").item(0).getTextContent();
                String password = user.getElementsByTagName("password").item(0).getTextContent();

                decisions.add(new Decision(rates, accpetance, reviewText, new FAE(new User(name, username, email, password))));
            }

            Element keyword = (Element) app.getElementsByTagName("keywords").item(0);

            NodeList keywords = keyword.getElementsByTagName("keyword");

            List<Keyword> keys = new ArrayList<>();
            for (int k = 0; k < keywords.getLength(); k++) {
                keys.add(new Keyword(keywords.item(k).getTextContent()));

            }
            String uk = "Desconhecido";
            Application finalApp = new Application(new Representative(new User(uk, uk, uk, uk)), "Application" + appN, description, invites, area, new KeywordList(keys));
            DecisionList dl = finalApp.getDecisionList();
            decisions.forEach(dl::addDecision);
            appN++;
            applicationsList.add(finalApp);

        }

        return applicationsList;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public List<FAE> getFaeList() {
        return faeList;
    }

    public List<Stand> getStandList() {
        return standList;
    }

    public List<Application> getApplicationsRegister() {
        return applicationsRegister;
    }

    private void importEventDataFormXMLFile() throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        Element rootTag = doc.getDocumentElement();

        if ("event".equals(rootTag.getNodeName())) {

            eventTitle = rootTag.getElementsByTagName("title").item(0).getTextContent();

            NodeList stands = rootTag.getElementsByTagName("stands");

            standList = readStandList(stands);

            NodeList faeSet = rootTag.getElementsByTagName("FAESet");

            faeList = readFaeSet(faeSet);

            NodeList appSet = rootTag.getElementsByTagName("applicationSet");

            applicationsRegister = readApplicationSet(appSet);

        } else {
            throw new Exception("File doesn't contain the correct format");
        }

    }

}
