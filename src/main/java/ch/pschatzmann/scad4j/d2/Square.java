package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.d3.Cube;

/**
 * Creates a square or rectangle in the first quadrant. When center is true the
 * square is centered on the origin. 
 * 
 * 
 * @author pschatzmann
 *
 */
public class Square extends SCAD4JObject {
	private Object x;
	private Object y;
	private boolean center;

	public Square(SCAD scad) {
		super(scad);
	}

	public Square size(Object size) {
		y = size;
		x = size;
		return this;
	}

	public Square size(Object x, Object y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	
	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Square center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Square center(boolean value) {
		center = value;
		return this;
	}

	/**
	 * Determines if the object is centered
	 * @return true if it is centered
	 */
	public boolean isCenter() {
		return center;
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
