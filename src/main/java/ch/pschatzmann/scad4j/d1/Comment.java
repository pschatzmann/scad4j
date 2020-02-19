package ch.pschatzmann.scad4j.d1;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Adds a comment to the scad file
 * @author pschatzmann
 *
 */
public class Comment extends SCAD4JObject {
	private String comment;

	public Comment(SCAD scad) {
		super(scad);
	}

	public ISCAD comment(String comment) {
		this.comment = comment;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		if (!comment.contains("\n")) {
		sb.append("// ");
		sb.append(comment);
		} else {
			sb.append("/* ");
			sb.append(comment);
			sb.append(" */");
		}
	}

}
