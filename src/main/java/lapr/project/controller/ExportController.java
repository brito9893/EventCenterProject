package lapr.project.controller;

import java.io.File;
import lapr.project.model.EventCenter;
import lapr.project.utils.XMLMarshaller;

/**
 * Export Controller
 */
public class ExportController {

    /**
     * Event Center object
     */
    private EventCenter eventCenter;
    /**
     * Path to export file
     */
    private String path;

    /**
     * Constructor
     *
     * @param eventCenter : Event Center
     */
    public ExportController(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    /**
     * Sets the path to the export file
     *
     * @param path : Path to export file
     */
    public void setFile(String path) {
        this.path = path;
    }

    /**
     * Exports all the information to a file
     *
     * @param key : Encryption key
     * @return : true if file was successfully exported
     */
    public boolean exportFile(String key) {
        try {
            File file = new File(path);

            XMLMarshaller.exportEventCenterData(eventCenter, file, key);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
