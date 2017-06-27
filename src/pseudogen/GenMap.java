/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Godu92
 */
public class GenMap extends Base {
	
	Map<String, List<String>> map = new HashMap<String, List<String>>();
	List<Integer> index = new ArrayList<Integer>();
	
	public void makeMap(File arrayDir, boolean verbose) {
		readArrays(arrayDir, verbose);
	}
	
	public static String searchAndReplace(List<Integer> count, String str,
			String strValue, Map<String, List<String>> map) {
		int i = 0;
		String newtext = str.replace("<time>", strValue);
		str = newtext;
		
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			
			newtext = str.replace(key, values.get(count.get(i)));
			str = newtext;
			
			if (i == 0) {
				print("Replaced "
						+ key
						+ " with "
						+ values.get(count.get(i))
						+ " | ");
			} else {
				print(key + " with " + values.get(count.get(i)));
				if (i < (map.size() - 1)) {
					print(" | ");
				}
			}
			i++;
		}
		print("");
		return str;
	}
	
	private List<Integer> calcIndex(int filecount, List<String> set,
			boolean verbose, Map<String, List<String>> map) {
		for (int i = 0; i < set.size(); i++) {
			int y = (map.get(set.get(i)).size());
			index.add(filecount % y);
			filecount = Math.round(filecount / y);
		}
		if (verbose == true) {
			print("Array index calculated");
		}
		return index;
	}
	
	private void generateMap(File file, Map<String, List<String>> map) {
		String key = null;
		List<String> info = new ArrayList<String>();
		
		// if(file.isFile() && file.getName().endsWith(".txt")){
		// Scanner s = null;
		// try{
		// s = new Scanner(file);
		// }catch (FileNotFoundException e){
		// message = "Could not get file: " + file.toString();
		// error(message, e);
		// }
		//
		// int line = 0;
		// while(s.hasNextLine()){
		// String r = s.nextLine();
		// String g;
		// if(!(r.startsWith("#"))){
		// if(r.contains("\\n")){
		// g = r.replace("\\n", System.lineSeparator());
		// r = g;
		// }
		// if(line == 0);{
		// key = r;
		// }else{
		// info.add(r);
		// }
		// line++;
		// }
		// }
		// map.put(key, info);
		// s.close();
		// }
	}
	
	private Map<String, List<String>> readArrays(File arrayDir,
			boolean verbose) {
		if (arrayDir != null) {
			for (File file : arrayDir.listFiles()) {
				generateMap(file, map);
			}
			if (verbose == true) {
				print("Map generate from directory");
			}
		}
		
		List<String> arrayFile = new ArrayList<>();
		
		// arrayFile = GetCommand.getArrayFiles();
		
		if (arrayFile.size() > 0) {
			for (int i = 0; i < arrayFile.size(); i++) {
				File file = new File(arrayFile.get(i));
				generateMap(file, map);
			}
			if (verbose == true) {
				print("Map generated from files");
			}
		}
		if (verbose == true) {
			print("Arrays read in");
		}
		
		return map;
	}
	
	public Map<String, List<String>> getMap() {
		return this.map;
	}
}
