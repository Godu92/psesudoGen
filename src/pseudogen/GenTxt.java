/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Godu92
 */
public class GenTxt extends Base {
	
	@Override
	public void create() {
		try {
			createTemp(null, false, null, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File out = getTarget();
		
		format();
	}
	
	private void format() {
		// parser into expected format
		message = "Format parser not supported yet.";
		error(message, null);
	}
}
