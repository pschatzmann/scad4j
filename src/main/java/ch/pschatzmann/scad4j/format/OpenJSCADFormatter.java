package ch.pschatzmann.scad4j.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Formatter using the OpenJSCAD command line to convert the data
 * 
 * openjscad cube.scad -o cube.jscad 
 * 
 * @author pschatzmann
 *
 */
public class OpenJSCADFormatter implements IFormatter {
	private static Boolean isInstalled = false;
	private String command = "openjscad";

	@Override
	public InputStream formatToStream(ISCAD obj, String targetFormat) throws IOException, InterruptedException {
		File tempFile = File.createTempFile("tmp-", "." + targetFormat);
		formatToFile(obj, targetFormat, tempFile);
		return new FileInputStream(tempFile);
	}

	@Override
	public void formatToFile(ISCAD obj, String targetFormat, File resultFile) throws IOException, InterruptedException {
		String scadString = obj.toString();
		File scadFile = Utils.createTempFile(scadString, "scad");
		if (!isInstalled()) {
			install();
		}
		Utils.execCommand(Utils.getCommand("openjscad_path",command), scadFile, resultFile);
	}

	/**
	 * Checks if openjscad is already installed 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */

	protected boolean isInstalled() throws IOException, InterruptedException {
		if (isInstalled == false) {
			Process p = Runtime.getRuntime().exec("which "+command);
	        int exitVal = p.waitFor();
	        if (exitVal!=0) {
	        	isInstalled = false;
	        } else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		        String line = "";
		        while ((line = reader.readLine()) != null) {
		           line += line;
		        }
		        isInstalled =  !Utils.isEmpty(line);
		        if (isInstalled) {
		        	System.out.println("We will use "+line);
		        } else {
		        	System.out.println("The program '"+command+"' is not installed yet...");		        	
		        }
	        }
		}
		return isInstalled;
	}
	
	/**
	 * Installs @jscad/cli
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected void install() throws IOException, InterruptedException {
		if (!isInstalled) {
			Process p = Runtime.getRuntime().exec("npm install -g @jscad/cli");
	        int exitVal = p.waitFor();
	        if (exitVal==0) {
	        	System.out.println("The program '"+command+"' is now available!");
	        	OpenJSCADFormatter.isInstalled = true;
	        }			
		}
	}

}
