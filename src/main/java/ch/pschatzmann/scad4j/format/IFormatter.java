package ch.pschatzmann.scad4j.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Translation between file formats. This is currently done with the help of
 * SCAD or JSCAD. The result is provided as InputStream and as File
 * 
 * @author pschatzmann
 *
 */
public interface IFormatter extends Serializable {
	public InputStream formatToStream(ISCAD obj, String targetFormat) throws IOException, InterruptedException;

	public void formatToFile(ISCAD obj, String targetFormat, File out) throws IOException, InterruptedException;
}
