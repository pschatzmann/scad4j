package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

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
	private Object degrees;

	public ActionRotate(ISCAD obj) {
		super(obj);
	}
	

	public ActionRotate transform(Object degrees) {
		this.degrees = degrees;
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

		sb.append(") ");
	}

}
