/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudogen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Godu92
 */
public class Base extends PseudoGen {
	
	Base base;
	public final static String dateFormat = "yyyyMMdd";
	public final static String timeFormat = "HH:mm:ss:SSS";
	public final static SimpleDateFormat sdfDate =
			new SimpleDateFormat(dateFormat);
	public final static SimpleDateFormat sdfTime =
			new SimpleDateFormat(timeFormat);
	
	public static String getDate(Date now) {
		String timeStamp = sdfDate.format(now);
		return timeStamp;
	}
	
	public static String getTime(Date now) {
		String timeStamp = sdfTime.format(now);
		return timeStamp;
	}
	
	public static String randString(int num) {
		String str = "";
		for (int i = 0; i < num; i++) {
			int ch = (int) (Math.random() * ('Z' - 'A' + 1)) + 'A';
			str += (char) ch;
		}
		return str;
	}
	
	public int countFiles(File target, String format) throws IOException {
		List<File> files = new ArrayList<>();
		DirectoryStream<Path> stream;
		
		if (format.equals("text")) {
			stream = Files.newDirectoryStream(target.toPath(), "*.txt");
		} else if (format.equals("doc")) {
			stream = Files.newDirectoryStream(target.toPath(), "*.doc");
		} else {
			stream = null;
		}
		
		try {
			for (Path file : stream) {
				files.add(file.toFile());
			}
		} finally {
			stream.close();
		}
		
		return files.size();
	}
	
	public void createTemp(String template, boolean verbose, String format,
			int filecount, Map<String, List<String>> map) throws IOException {
		File sample = new File(template);
		
		Date now = new Date();
		String strValue = null;
		if (format.equals("text")) {
			strValue = "---"
					+ "Date: "
					+ getDate(now)
					+ "   "
					+ "Time: "
					+ getTime(now)
					+ "---";
		} else if (format.equals("doc")) {
			strValue = getDate(now) + " " + getTime(now);
		}
		
		InputStream is = new FileInputStream(sample);
		byte[] data = new byte[(int) sample.length()];
		is.read(data);
		
		String str = new String(data, "UTF-8");
		is.close();
		
		List<Integer> count = new ArrayList<>();
		List<String> set = new ArrayList<String>(map.keySet());
		
		// count = GenMap.calcArrays(set, verbose, filecount, map);
		
		File temp = writeTemp(str, strValue, format, count, map);
		
		sample = temp;
		sample.deleteOnExit();
	}
	
	public File getNextFile(File target, AtomicLong indexCount, String format) {
		File next;
		
		do {
			long index = indexCount.incrementAndGet();
			
			String filename = null;
			if (format.equals("text")) {
				filename = "Target_" + String.format("%06f", index) + ".txt";
			} else if (format.equals("doc")) {
				filename = "Target_" + String.format("%06f", index) + ".doc";
			}
			
			next = new File(target, filename);
		} while (next.exists());
		
		return next;
	}
	
	public File writeTemp(String str, String strValue, String format,
			List<Integer> count, Map<String, List<String>> map)
			throws IOException {
		File out = File.createTempFile("Temp", ".txt");
		
		FileWriter fw = new FileWriter(out);
		
		String newtext = GenMap.searchAndReplace(count, strValue, str, map);
		
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
	
	static ArrayList<String> getArrayFiles() {
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
	
	public Map<String, List<String>> makeMap(File arrayDir,
			ArrayList<String> arrayFiles) {
		message = "Not supported yet.";
		error(message, null);
		return null;
	}
}
