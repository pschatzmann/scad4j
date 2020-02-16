package ch.pschatzmann.scad4j.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Formatter using the OpenSCAD command line to convert the data
 * 
 * @author pschatzmann
 *
 */
public class OpenSCADFormatter implements IFormatter {
	private String command = "openscad";

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
		String cmd = Utils.getCommand("openscad_path",command) + " -o " + output.getAbsolutePath() + " " + input.getAbsolutePath();
		Process p = Runtime.getRuntime().exec(cmd);
        Utils.displayErrors(p);
	}
	




}
