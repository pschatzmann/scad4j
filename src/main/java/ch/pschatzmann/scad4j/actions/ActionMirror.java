package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;

/*
 * Mirrors the child element on a plane through the origin. The argument to mirror() 
 * is the normal vector of a plane intersecting the origin through which to mirror the object.
 *
 */

public class ActionMirror extends ActionTransform {
	
	public ActionMirror(ISCAD obj) {
		super(obj);
	}
		
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("mirror(v=");
		sb.append(SCAD.xyz(x,y,z));
		sb.append(") ");
	}

}
