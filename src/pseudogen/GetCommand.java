/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Godu92
 */
public class GetCommand extends Base {

    private File template = null, arrayDir = null, target = null;
    String format = null;
    private boolean verbose = false;
    private final ArrayList<String> arrayFiles = new ArrayList<>();

    @Override
    public void commandLine(String[] args) {
        int i = 0;

        String debug = Arrays.toString(args);
        if (debug.contains("-verbose")) {
            verbose = true;
            print("Printing verbose statements");
            print("Arguments: " + Arrays.toString(args));
        } else {
            debug = null;
        }

        format = args[i].toLowerCase();
        if (verbose == true) {
            print("Format is: " + format);
        }
        i++;

        template = new File(args[i]);
        if (template.isFile()) {
            if (verbose == true) {
                print("Template file found: " + template.toString());
            }
        } else {
            message = "Argument: " + args[i] + " is not a file";
            error(message);
        }
        i++;

        target = new File(args[i]);
        if (target.isDirectory()) {
            if (verbose == true) {
                print("Target directory found: " + target.toString());
            }
        } else {
            message = "Argument: " + args[i] + " is not a directory";
            error(message);
        }
        i++;

        while (i < args.length) {
            String test = args[i];
            if (test.equalsIgnoreCase("-arrayDir")) {
                test = args[++i];
                arrayDir = new File(test);
                if (!(arrayDir.isDirectory())) {
                    message = "Argument: " + args[i] + " is not a directory";
                    error(message);
                } else if (verbose == true) {
                    print("Array directory read in");
                }
            } else if (test.equalsIgnoreCase("-arrays")) {
                int start = i;
                while (!(test.contains("-")) && i < args.length) {
                    File arrayFile = new File(test);
                    if (arrayFile.isFile()) {
                        arrayFiles.add(test);
                        if (verbose == true) {
                            print("Array files read in");
                        }
                    } else {
                        message = "Argument: " + args[i] + " is not a file";
                        error(message);
                    }
                    i++;
                }
                i = start;
            }
            i++;
        }
    }

    @Override
    String getFormat() {
        return format;
    }

    @Override
    File getTemplate() {
        return template;
    }

    @Override
    File getTarget() {
        return target;
    }

    @Override
    File getArrayDir() {
        return arrayDir;
    }

    @Override
    ArrayList<String> getArrayFiles() {
        return arrayFiles;
    }

    @Override
    boolean getVerbose() {
        return verbose;
    }
}
