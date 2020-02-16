package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.d1.ISCAD;
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
	private Double x,y,z;
	
	public ISCAD size(double size) {
		this.x = size;
		this.y = size;
		this.z = size;
		
		return this;
	};

	public ISCAD size(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	};

	public ISCAD size(int size[]) {
		return this.size(size[0],size[1],size[2]);
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
