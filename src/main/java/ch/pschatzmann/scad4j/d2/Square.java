package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Creates a square or rectangle in the first quadrant. When center is true the
 * square is centered on the origin. 
 * 
 * 
 * @author pschatzmann
 *
 */
public class Square extends SCAD4JObject {
	private double x;
	private double y;

	public Square(SCAD scad) {
		super(scad);
	}

	public Square size(double size) {
		y = size;
		x = size;
		return this;
	}

	public Square size(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(), sb);
		sb.append("square(size=[");
		sb.append(x);
		sb.append(",");
		sb.append("y");
		sb.append(", center=");
		sb.append(isCenter());
		sb.append("); ");
	}

}
