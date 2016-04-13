/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Godu92
 */
public class GenMap extends Base {

    @Override
    public void makeMap(File arrayDir, ArrayList<String> arrayFiles) {
        readArrays(arrayDir, arrayFiles);
        generateMap();
        calcIndex();
        searchAndReplace();
    }

    private void searchAndReplace() {
        message = "Not supported yet.";
        error(message, null);
    }

    private void calcIndex() {
        message = "Not supported yet.";
        error(message, null);
    }

    private void generateMap() {
        message = "Not supported yet.";
        error(message, null);
    }

    private void readArrays(File arrayDir, ArrayList<String> arrayFiles) {
        message = "Not supported yet.";
        error(message, null);
    }
}
