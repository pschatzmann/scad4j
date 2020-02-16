package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Linear extrude on a 2D object
 * 
 * @author pschatzmann
 *
 */
public class ActionLinearExtrude extends ObjectSourceAction {
	private Double height = 10.0;
	private Double convexity;
	private Double twist;
	private Integer slices;
	private Double scale;
	private Double resolution;

	public ActionLinearExtrude(ISCAD obj) {
		super(obj);
	}

	public ActionLinearExtrude height(double height) {
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
