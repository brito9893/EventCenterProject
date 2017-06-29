/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.*;

import javax.xml.bind.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

/**
 * @author U
 */
public class XMLMarshaller {

    private static final Logger LOGGER = Logger.getLogger(XMLMarshaller.class.getName());

    private XMLMarshaller() {
    }

    public static void exportEventCenterData(EventCenter center, File file, String key) throws Exception {

        JAXBContext jc = JAXBContext.newInstance(EventCenter.class);
        Marshaller mash = jc.createMarshaller();
        mash.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        mash.marshal(center, sw);
        FileCipher fc = new FileCipher(key);
        String encrypted = fc.cipher(sw.toString());
        Files.write(Paths.get(file.getPath()), encrypted.getBytes());
    }

    public static void importEventCenterData(EventCenter center, File file, String key) throws Exception {
        if (file.exists()) {
            JAXBContext jc = JAXBContext.newInstance(EventCenter.class);
            Unmarshaller unMash = jc.createUnmarshaller();
            String xmlFile = fileToString(file);
            FileCipher fc = new FileCipher(key);
            String decrypted = fc.decipher(xmlFile);
            StringReader reader = new StringReader(decrypted);
            EventCenter readCenter = (EventCenter) unMash.unmarshal(reader);

            center.setEventRegister(readCenter.getEventRegister());
            center.setUserNotConfirmedRegister(readCenter.getUserNotConfirmedRegister());
            center.setUserRegister(readCenter.getUserRegister());
            center.setEventManagers(readCenter.getEventManagers());
        } else {
            throw new FileNotFoundException("File not found.");
        }
    }

    private static String fileToString(File file) throws FileNotFoundException {
        StringBuilder build = new StringBuilder();
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                build.append(scan.nextLine());
                build.append("\n");
            }

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.toString());
            return "";
        }
        return build.toString();
    }
}
