package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.format.Utils;
import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;

/**
 * Creates a cube in the first octant. When center is true, the cube is centered
 * on the origin. Argument names are optional if given in the order shown here.
 * 
 * 
 * @author pschatzmann
 *
 */
public class Cube extends SCAD4JObject {
	private Object x,y,z;
	private boolean center;

	public Cube(SCAD scad) {
		super(scad);
	}

	public Cube size(Number ...x) {
		return size(Utils.toStringArray(x));
	}

	public Cube size(String...sa) {
		x = y = z = null;
		if (sa.length==1) {
			x = y = z = sa[0];
		} else if (sa.length==2) {
			this.x = sa[0];
			this.y = sa[1];
		} else {
			this.x = sa[0];
			this.y = sa[1];
			this.z = sa[2];
		}
		return this;
	}
	
	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	/**
	 * Centers the object (center = true)
	 * @return SCADObject
	 */
	public Cube center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Cube center(boolean value) {
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
		appendActions(this.getActions(),sb);
		sb.append("cube(");
		sb.append(SCAD.xyz(x,y,z));
		if (this.isCenter()) {
			sb.append(",center=");
			sb.append(isCenter());
		}
		sb.append("); ");
	}

}
