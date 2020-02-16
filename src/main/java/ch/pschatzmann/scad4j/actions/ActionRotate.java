package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Rotates its child 'a' degrees about the axis of the coordinate system or
 * around an arbitrary axis. The argument names are optional if the arguments
 * are given in the same order as specified.
 * 
 * 
 * @author pschatzmann
 *
 */
public class ActionRotate extends ActionTransform {
	private Double degrees;
	private Boolean[] v;

	public ActionRotate(ISCAD obj) {
		super(obj);
	}

	public ActionRotate transform(double degrees) {
		this.degrees = degrees;
		return this;
	}

	public ActionRotate values(double x, double y, double z) {
		super.values(x, y, z);
		return this;
	}

	@Override
	public ActionRotate values(double degrees[]) {
		super.values(degrees);
		return this;
	}

	public ActionRotate v(boolean x, boolean y, boolean z) {
		v = new Boolean[3];
		v[0] = x;
		v[1] = y;
		v[2] = z;
		return this;
	}

	public ActionRotate v(Boolean v[]) {
		this.v = v;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("rotate(a=");
		if (degrees != null) {
			sb.append(degrees);
		} else {
			sb.append(xyz());
		}

		if (v != null) {
			sb.append(", v=[");
			sb.append(v[0]);
			sb.append(",");
			sb.append(v[1]);
			sb.append(",");
			sb.append(v[2]);
			sb.append("]");
		}
		sb.append(") ");
	}

}
