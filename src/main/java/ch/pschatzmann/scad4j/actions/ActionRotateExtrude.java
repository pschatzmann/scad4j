package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * Rotational extrusion spins a 2D shape around the Z-axis to form a solid which
 * has rotational symmetry. 
 * 
 * @author pschatzmann
 *
 */
public class ActionRotateExtrude extends ObjectSourceAction {
	private ISCAD obj;
	double angle, convexity;

	ActionRotateExtrude(ISCAD obj) {
		super(obj);
	}

	public ActionRotateExtrude angle(double angle) {
		this.angle = angle;
		return this;
	}

	public ActionRotateExtrude convexity(double convexity) {
		this.convexity = convexity;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("rotate_extrude(angle=");
		sb.append(angle);
		sb.append(", convexity=");
		sb.append(convexity);
		sb.append(") ");
	}


}
