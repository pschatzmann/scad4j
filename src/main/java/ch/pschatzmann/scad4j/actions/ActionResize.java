package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Modifies the size of the child object to match the given x,y, and z.
 * 
 * 
 * @author pschatzmann
 *
 */
public class ActionResize extends ActionTransform {
	Boolean autoX;
	Boolean autoY;
	Boolean autoZ;

	public ActionResize(ISCAD obj) {
		super(obj);
	}

	public ActionResize auto(boolean auto) {
		this.autoX = auto;
		this.autoY = auto;
		this.autoZ = auto;
		return this;
	}

	public ActionResize auto(boolean x, boolean y, boolean z) {
		this.autoX = x;
		this.autoY = y;
		this.autoZ = z;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("resize(newsize=[");
		sb.append(x);
		sb.append(",");
		sb.append(y);
		if (z != null) {
			sb.append(",");
			sb.append(z);
		}
		sb.append("]");
		if (this.autoX != null) {
			sb.append(", auto=[");
			sb.append(autoX);
			sb.append(",");
			sb.append(autoY);
			if (this.autoZ != null) {
				sb.append(",");
				sb.append(autoZ);
			}
			sb.append("]");
		}
		sb.append(") ");
	}

}
