package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.format.Utils;

/**
 * Displays the child elements using the specified RGB color + alpha value. This
 * is only used for the F5 preview as CGAL and STL (F6) do not currently support
 * color. The alpha value will default to 1.0 (opaque) if not specified.
 * 
 * 
 * @author pschatzmann
 *
 */

public class ActionColor extends ObjectSourceAction {
	private Object str = "";
	private Object r, g, b, alpha;

	public ActionColor(ISCAD obj) {
		super(obj);
	}

	public ActionColor values(Object name, Object alpha) {
		this.str = name;
		this.alpha = alpha;
		return this;
	}

	public ActionColor values(Object r, Object g, Object b, Object alpha) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.alpha = alpha;
		return this;
	}

	public ActionColor values(Object hexValue) {
		this.str = hexValue;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append(" color(");
		if (str != null && !Utils.isEmpty(str)) {
			sb.append("\"");
			sb.append(str);
			sb.append("\"");
		}
		if (alpha != null) {
			sb.append(",");
			sb.append(alpha);
		}
		if (r != null) {
			sb.append("[");
			sb.append(r);
			sb.append(",");
			sb.append(g);
			sb.append(",");
			sb.append(b);
			sb.append(",");
			sb.append(alpha);
		}
		sb.append(") ");
	}

}
