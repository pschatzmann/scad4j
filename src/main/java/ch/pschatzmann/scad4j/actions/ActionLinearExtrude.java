package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * Linear extrude on a 2D object
 * 
 * @author pschatzmann
 *
 */
public class ActionLinearExtrude extends ObjectSourceAction {
	private Object height = "10.0";
	private Object convexity;
	private Object twist;
	private Object slices;
	private Object scale;
	private Object resolution;

	public ActionLinearExtrude(ISCAD obj) {
		super(obj);
	}

	public ActionLinearExtrude height(Object height) {
		this.height = height;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("linear_extrude(");
		if (height != null) {
			sb.append("height=");
			sb.append(height);
		}
		sb.append(") ");
	}
	

}
