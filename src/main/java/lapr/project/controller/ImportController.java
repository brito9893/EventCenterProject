package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.*;

import java.io.*;

/**
 * Created by Mário Vaz
 */
public class ImportController {

    /**
     * Event Center object
     */
    private final EventCenter eventCenter;
    /**
     * Path to import file
     */
    private String path;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    public ImportController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * Sets the path to the import file
     *
     * @param path : String with path
     */
    public void setFile(String path) {
        this.path = path;
    }

    /**
     * Imports a file
     *
     * @param key : Encryption key
     * @return : returns if the file was successfully imported
     */
    public boolean importFile(String key) {
        try {
            File file = new File(path);

            if (file.exists()) {
                XMLMarshaller.importEventCenterData(eventCenter, file, key);
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }
    }
}
