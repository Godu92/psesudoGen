/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;

/**
 *
 * @author Godu92
 */
public class GenTxt extends Base {

    @Override
    public void create() {
        createTemp();

        File template = null;
        writeTemp(template);

        format();
    }

    private void format() {
        message = "Not supported yet.";
        error(message, null);
    }
}