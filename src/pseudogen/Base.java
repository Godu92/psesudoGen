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

    public void createTemp(String template, boolean verbose, String format) {
        File sample = new File(template);
        
        Date now = new Date();
        String strValue = null;
        if(format.equals("text")){
            strValue = "---" + "Date: " + getDate(now) + "   " + "Time: " + getTime(now) + "---";
        }else if(format.equals("doc")){
            strValue = getDate(now) + " " + getTime(noW);
        }
        
        InputStream is = new FileInputStream(sample);
        byte[] data = new bayte[(int) sample.length()];
        is.read(data);
        
        String str = new String(data, "UTF-8");
        is.close();
        
        List<Integer> count;
        List<String> set = new ArrayList<String>(map.keySet());
        
        count = GenMap.calcArrays(set, verbose);
        
        File temp = writeTemp(str, strValue, format);
        
        sample = temp;
        sample.deleteOnExit();
        return sample;
    }

    public void writeTemp(String str, String strValue, String format) {
        File out = File.createTempFile("Temp", ".txt");
        
        FileWriter fw = new FileWriter (out);
        
        String newtext= GenMap.searchAndReplace(str, strValue);
        
        fw.write(newtext + "\n");
        fw.close();
        return out;
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
