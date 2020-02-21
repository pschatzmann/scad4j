package ch.pschatzmann.scad4j.d1;

import java.io.File;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Adds a comment to the scad file
 * @author pschatzmann
 *
 */
public class Use extends SCAD4JObject {
	private String filePath;

	public Use(SCAD scad) {
		super(scad);
	}

	public ISCAD importFile(File file) {
		this.filePath = file.getAbsolutePath();
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);
		sb.append("use(\"");
		sb.append(filePath);
		sb.append("\");");
		sb.append(System.lineSeparator());
				
	}

}
