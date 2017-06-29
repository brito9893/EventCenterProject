/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.*;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author U
 */
public class XMLMarshallerTest {

    @Test
    public void testMarshallerExport() throws Exception {

        EventCenter center = new EventCenter();
        EventCenter center2 = new EventCenter();
        File test = new File("./src/test/resources/test.xml");
        String key = "key";
        XMLMarshaller.exportEventCenterData(center, test, key);

        XMLMarshaller.importEventCenterData(center2, test, key);

        assertEquals(center, center2);

    }

    @Test(expected = Exception.class)
    public void testMarshallerImportNonExistingFile() throws Exception {

        EventCenter center = new EventCenter();
        EventCenter center2 = new EventCenter();
        File test = new File("./src/test/resources/test22.xml");
        String key = "key";

        XMLMarshaller.importEventCenterData(center2, test, key);


    }

}
