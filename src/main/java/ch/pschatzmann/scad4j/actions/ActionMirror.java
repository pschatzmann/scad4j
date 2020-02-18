package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;

/*
 * Mirrors the child element on a plane through the origin. The argument to mirror() 
 * is the normal vector of a plane intersecting the origin through which to mirror the object.
 *
 */

public class ActionMirror extends ActionTransform {
	private double x,y,z;
	
	public ActionMirror(ISCAD obj) {
		super(obj);
	}
	
	public ActionMirror values(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("mirror(v=");
		sb.append(SCAD.xyz(x,y,z));
		sb.append(") ");
	}


}
