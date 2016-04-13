/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author Godu92
 */
public class Base extends PseudoGen {

    Base base;

    public void createTemp() {
        try {
            Files.createTempFile("TempFile", ".txt");
        } catch (IOException ex) {
            message = "Error creating temp file";
            error(message, ex);
        }
    }

    public void writeTemp(File template) {
        base = new GenMap();

        base.searchAndReplace(template);
    }

    public void commandLine(String[] args) {
        message = "Not supported yet.";
        error(message, null);
    }

    public void create() {
    }

    String getFormat() {
        String format = null;
        return format;
    }

    File getTemplate() {
        File template = null;
        return template;
    }

    File getTarget() {
        File target = null;
        return target;
    }

    File getArrayDir() {
        File arrayDir = null;
        return arrayDir;
    }

    ArrayList<String> getArrayFiles() {
        ArrayList<String> arrays = null;
        return arrays;
    }

    boolean getVerbose() {
        boolean verbose = false;
        return verbose;
    }

    public void searchAndReplace(File template) {
        message = "Not supported yet.";
        error(message, null);
    }

    public void makeMap(File arrayDir, ArrayList<String> arrayFiles) {
        message = "Not supported yet.";
        error(message, null);
    }
}
