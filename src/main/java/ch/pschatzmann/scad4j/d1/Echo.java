package ch.pschatzmann.scad4j.d1;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Adds the echo command to the scad file
 * @author pschatzmann
 *
 */
public class Echo extends SCAD4JObject {
	private String echo;

	public Echo(SCAD scad) {
		super(scad);
	}
	
	public ISCAD value(String value) {
		// escape " characters
		this.echo = value.replaceAll("\"","\\\\");
		// escape " characters
		this.echo = value.replaceAll("\"","\\");
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		sb.append("echo(");
		sb.append(echo);
		sb.append(")");
		
	}
}
