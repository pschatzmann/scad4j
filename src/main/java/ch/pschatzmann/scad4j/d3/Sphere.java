package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Creates a sphere at the origin of the coordinate system. The r argument name
 * is optional. To use d instead of r, d must be named.
 * 
 * 
 * @author pschatzmann
 *
 */
public class Sphere extends SCAD4JObject {
	private Object radius;
	private Object diameter;
	private Object fragmentAngle;
	private Object fragemntSize;
	private Object resolution;
	private boolean addSeparator=false;
	private boolean center;

	public Sphere(SCAD scad) {
		super(scad);
	}

	public Sphere radius(Object radius) {
		this.radius = radius;
		return this;
	}

	public Sphere diameter(Object diameter) {
		this.diameter = diameter;
		return this;
	}


	public Sphere fragmentAngle(Object fragmentAngle) {
		this.fragmentAngle = fragmentAngle;
		return this;
	}

	public Sphere resolution(Object resolution) {
		this.resolution = resolution;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		this.addSeparator=false;
		sb.append("sphere(");
		append(sb,"r",this.radius);
		append(sb,"d",this.diameter);
		append(sb,"$fa" ,this.fragmentAngle);
		append(sb,"$fs",this.fragemntSize);
		append(sb,"$fn",this.resolution);
		if (this.isCenter()) {
			append(sb,"center",this.isCenter());
		}		
		sb.append("); ");
	}

	private void append(StringBuffer sb, String name, Object value) {
		if (value!=null) {
			if (addSeparator) {
				sb.append(",");
			}
			sb.append(name);
			sb.append("=");
			sb.append(value);
			addSeparator = true;
		}
	}
	
	
	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Sphere center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Sphere center(boolean value) {
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

}
