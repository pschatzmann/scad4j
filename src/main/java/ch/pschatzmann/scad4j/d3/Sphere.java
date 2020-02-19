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
	private Double radius;
	private Double diameter;
	private Double fragmentAngle;
	private Double fragemntSize;
	private Double resolution;
	private boolean addSeparator=false;

	public Sphere(SCAD scad) {
		super(scad);
	}


	public Sphere radius(double radius) {
		this.radius = radius;
		return this;
	}

	public Sphere diameter(double diameter) {
		this.diameter = diameter;
		return this;
	}

	public Sphere fragmentAngle(double fragmentAngle) {
		this.fragmentAngle = fragmentAngle;
		return this;
	}

	public Sphere resolution(double resolution) {
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
	 * Centers the object (center = true)
	 * @return SCADObject
	 */
	@Override
	public ISCAD center() {
		throw new RuntimeException("Center not supported by Sphere");
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	@Override
	public ISCAD center(boolean value) {
		throw new RuntimeException("Center not supported by Sphere");
	}

	/**
	 * Determines if the object is centered
	 * @return true if it is centered
	 */
	@Override
	public boolean isCenter() {
		return false;
	}
	

}
