package ch.pschatzmann.scad4j.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * Formatter using the OpenSCAD command line to convert the data
 * 
 * @author pschatzmann
 *
 */
public class OpenSCADFormatter implements IFormatter {
	private String command = "openscad";
	private static boolean isSetup = false;
	private int Display=0;

	public OpenSCADFormatter() throws IOException, InterruptedException {
		if (!isSetup) {
			setup();
		}
	}

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
		this.execOpenSCAD(scadFile, resultFile);
	}

	protected void execOpenSCAD(File input, File output) throws IOException, InterruptedException {
		String cmd = Utils.getCommand("openscad_path", command) + " -o " + output.getAbsolutePath() + " "
				+ input.getAbsolutePath();
		String[] envp = { "DISPLAY=:"+Display };
		Process p = Runtime.getRuntime().exec(cmd, envp);
		Utils.displayErrors(p);
	}

	protected void setup() {
		(new Thread() {
			public void run() {
					try {
						isSetup = true;
						
						String cmd = "xdpyinfo -display :"+Display;
						Process pCheck = Runtime.getRuntime().exec(cmd);
						String status = getOutput(pCheck);						
						if (status.indexOf("unable to open")>0) {
							Process p = Runtime.getRuntime().exec("Xvfb :"+Display+" -screen 0 800x600x24 ");
							// if we get here the process has ended
							Utils.displayErrors(p);						
							isSetup = false;
						}
					} catch (Exception ex) {
						System.err.println("Please make sure that xvfb is installed");
						ex.printStackTrace();
						isSetup = false;
					}
				
			}

			public String getOutput(Process p) throws IOException {
				StringBuffer sb = new StringBuffer();
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String line = null;
				while ((line = errorReader.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		}).start();
	}

}
