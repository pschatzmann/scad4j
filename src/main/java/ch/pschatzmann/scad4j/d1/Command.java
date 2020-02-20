package ch.pschatzmann.scad4j.d1;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Adds a comment to the scad file
 * @author pschatzmann
 *
 */
public class Command extends SCAD4JObject {
	private String command;

	public Command(SCAD scad) {
		super(scad);
	}

	public Command(SCAD scad, String command2) {
		super(scad);
		this.command = command2;
	}

	public ISCAD command(String comment) {
		this.command = comment;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);
		sb.append(command);
	}

}
