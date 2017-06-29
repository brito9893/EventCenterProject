/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.*;

/**
 * @author U
 */
public class SavedFileManager {

    /**
     * Default path to save file String
     */
    private static final String DEFAULT_PATH = "./src/main/resources/";

    /**
     * Temporary path String
     */
    private String tempPath;

    /**
     * Class constructor
     */
    SavedFileManager() {
        tempPath = DEFAULT_PATH;
    }

    /**
     * Save file
     *
     * @param file file to save
     * @return Logic value of the success of the operation
     */
    boolean savedFilePath(File file) {
        try {
            File tempFile = new File(tempPath + "EventCenter.tmp");

            // If file doesn't exists, then create it
            if (!tempFile.exists() && !tempFile.createNewFile()) {
                return false;
            }
            if (!file.exists()) {
                return false;
            }

            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(tempFile.getAbsoluteFile());
                bw = new BufferedWriter(fw);
                bw.write(file.getAbsolutePath());
            } catch (IOException io) {
                return false;
            } finally {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }

            }

            return true;

        } catch (IOException | NullPointerException io) {
            return false;
        }

    }

    /**
     * Import file path
     *
     * @param tempName temporary name
     * @return file path
     */
    public String importFilePath(String tempName) {
        String filePath = null;

        try {
            File tempFile = new File(tempPath + tempName);

            if (!tempFile.exists()) {
                return null;
            }

            FileReader fw = null;
            BufferedReader bw = null;

            try {
                fw = new FileReader(tempFile.getAbsoluteFile());
                bw = new BufferedReader(fw);
                filePath = bw.readLine();
            } catch (IOException io) {
                return null;
            } finally {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            }

            return filePath;

        } catch (IOException | NullPointerException io) {
            return filePath;
        }

    }

}
