/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Godu92
 */
public class GetCommand extends Base {
	
	private File template = null, arrayDir = null, target = null;
	int maxFiles = PseudoGen.MAX, delayNew = PseudoGen.DELAYNEW,
			delayFull = PseudoGen.DELAYFULL;
	String format = null;
	private boolean verbose = false, end = false, stop = false;
	private final List<String> arrayFiles = new ArrayList<>();
	
	@Override
	public void commandLine(String[] args) {
		int i = 0;
		
		if (args.length < 7 || args == null) {
			usagePrint();
			stop = true;
			return;
		}
		
		String debug = Arrays.toString(args).toLowerCase();
		
		// if args.length < minimum args to run, print usage doc and exit
		
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
				if (i < args.length) {
					test = args[++i];
					arrayDir = new File(test);
					if (!(arrayDir.isDirectory())) {
						message = "Argument: " + test + " is not a directory";
						error(message);
					} else if (verbose == true) {
						print("Array directory read in");
					}
				} else {
					print("-arayDir requires directory path");
				}
			} else if (test.equalsIgnoreCase("-arrays")) {
				int start = i;
				while (i < args.length) {
					test = args[++i];
					do {
						File file = new File(test);
						if (file.isFile()) {
							GetCommand.getArrayFiles().add(test);
							
						} else {
							message = "Argument: " + args[i] + " is not a file";
							error(message);
						}
						
						if (i < (args.length - 1)) {
							test = args[++i];
						} else {
							break;
						}
					} while (!(test.contains("-")));
					
					i = start;
				}
				// }else{
				// message = "-arrays requires at least one file";
				// error(message, null);
			} else if (test.equals("-max")) {
				test = args[++i];
				maxFiles = Integer.parseInt(test);
			} else if (test.equals("-new")) {
				test = args[++i];
				delayNew = Integer.parseInt(test);
			} else if (test.equals("-full")) {
				test = args[++i];
				delayFull = Integer.parseInt(test);
			} else if (test.equals("-stop")) {
				end = true;
			}
			i++;
		}
		if (verbose == true)
		
		{
			print("Command line processed");
		}
		return;
	}
	
	static void usagePrint() {
		print("Usage documentation");
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
	
	// @Override
	// ArrayList<String> getArrayFiles() {
	// return arrayFiles;
	// }
	
	@Override
	boolean getVerbose() {
		return verbose;
	}
	
	public int getMax() {
		return maxFiles;
	}
	
	int getNew() {
		return delayNew;
	}
	
	int getFull() {
		return delayFull;
	}
	
	boolean getEnd() {
		return end;
	}
	
	boolean getStop() {
		return stop;
	}
}
