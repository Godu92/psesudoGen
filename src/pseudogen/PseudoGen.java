/*
accepts command line/user input - class inherit base 2
reads in template - base
reads in search/replace data - class inherit base 1
get date and time - base
generate random string - base
stores data in map - class inherit base 1
calculates index to perform all combinations - base
performs search/replace - class inherit base 1
writes out file - base
stop running if file count hit or user interrupt - main
store default values for file count - main
read in internal resources - base
count files - base
error message - base
create/write to a temp file - base
getters - various classes

Pieces are split out into different classes
Some classes inherit from a base class

New function:
write out file in different formats (.txt, .docx, etc) - different classes for each

BaseName name = new InheritClass();
name.methodOfInherit();  <- this method needs to exist in both base and inherit class
 */
package pseudogen;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Godu92
 */
public class PseudoGen {

    public String message = null;
    public final static int MAX = 10, DELAYNEW = 100, DELAYFULL = 1000;
    public static boolean bstop;

    public void stop(){
        PseudoGen.bstop = true;
    }
    public void active(){
        return !PseudoGen.bstop;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Base base = new GetCommand();
        base.commandLine(args);

        String format = base.getFormat();
        File target = base.getTarget();
        File arrayDir = base.getArrayDir();
        ArrayList<String> arrayFiles = base.getArrayFiles();
        boolean verbose = base.getVerbose();
        Map<String, List<String>> map = base.getMap();
        int maxFiles = base.getMax();
        boolean end = base.getEnd();
        
        PseudoGen gen = new PseudoGen();
        
        if(end == false){
            print("Starting. Monitoring directory: " + target);
            String f = String.format("%5d"m maxFiles).replace(' ', '-');
            print("Maintaining: [" + f + "] file(s) in \"" + target + "\"");
            
            Thread interupt = new Thread("User Input"){
                public void run(){
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    try{
                        br.readLine();
                    }catch(IOException e){
                        error("Error in user interupt", e);
                    }
                    gen.stop();
                }
            };
            interupt.start();
            
            print("Press enter to stop");
        }else if (end == true){
            String f = String.format("%5d"m maxFiles).replace(' ', '-');
            print("Creating: [" + f + "] file(s) in \"" + target + "\"");
        }
        
        int filecount = 0;
        File template = base.getTemplate();
        int delayFull = base.getFull();
        int delayNew = base.getNew();
        
        try{
            while(gen.active()){
                int Count = base.countFiles(target, format);
                if(filecount <= maxFiles && end == true){
                    if (format.equals("text")) {
            base = new GenTxt();
            base.create(template, verbose, map, target, format, base);
        } else if (format.equals("doc")) {
            //base = new GenDoc();
            //base.create();
        }
        if(filecount == maxFiles){
            print("File max reached, program terminating");
            String f = String.format("%5d"m maxFiles).replace(' ', '-');
            print("[" + f + "] file(s) created");
            System.exit(1);
        }
            }else if (Count < maxFiles){
                if(filecount <= maxFiles && end == true){
                    if (format.equals("text")) {
            base = new GenTxt();
            base.create(template, verbose, map, target, format, base);
        } else if (format.equals("doc")) {
            //base = new GenDoc();
            //base.create();
        }
        Thread.sleep(delayNew);
            }else{
               Thread.sleep(delayFull); 
            }
            
        }
            }
        }catch(InteruptedException ex){
            System.exit(1);
        }catch(IOException e){
            error("", e);
        }
        
        print("User initiated stop, program terminating");
            String f = String.format("%5d"m maxFiles).replace(' ', '-');
            print("[" + f + "] file(s) created");
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void error(String message, Throwable t) {
        String entire;
        if (t == null) {
            entire = message;
        } else {
            entire = message + "\n" + t.getMessage();
        }
        error(entire);
    }

    public void error(String message) {
        print("--------------------------------------\n" + "Program encountered the following error:\n"
                + message + "\n--------------------------------------");
        System.exit(0);
    }

}
