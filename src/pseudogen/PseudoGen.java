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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {



        Base base = new GetCommand();
        base.commandLine(args);

        String format = base.getFormat();
        File template = base.getTemplate();
        File target = base.getTarget();
        File arrayDir = base.getArrayDir();
        ArrayList<String> arrayFiles = base.getArrayFiles();
        boolean verbose = base.getVerbose();

        base = new GenMap();
        base.makeMap(arrayDir, arrayFiles, verbose);

        if (format.equals("text")) {
            base = new GenTxt();
            base.create(template, verbose, map, target, format, base);
        } else if (format.equals("doc")) {
            //base = new GenDoc();
            //base.create();
        }

        base.print("\nEnd of program");
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
