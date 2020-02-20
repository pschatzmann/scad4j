package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.d2.ICircleCommon;

/**
 * Creates a cylinder or cone centered about the z axis. When center is true, it
 * is also centered vertically along the z axis.
 * 
 * Parameter names are optional if given in the order shown here. If a parameter
 * is named, all following parameters must also be named.
 * 
 * 
 * @author pschatzmann
 *
 */
public class Cylinder extends SCAD4JObject implements ICircleCommon {
	private Object h; // : height of the cylinder or cone
	private Object r; // : radius of cylinder. r1 = r2 = r.
	private Object r1; // : radius, bottom of cone.
	private Object r2; // radius, top of cone.
	private Object d; // : diameter of cylinder. r1 = r2 = d / 2. [Note: Requires version 2014.03]
	private Object d1; // : diameter, bottom of cone. r1 = d1 / 2. [Note: Requires version 2014.03]
	private Object d2; // : diameter, top of cone. r2 = d2 / 2. [Note: Requires version 2014.03]

	private Object minimumAngle; // : minimum angle (in degrees) of each fragment.
	private Object minimumCircumferentialLength; // : minimum circumferential length of each fragment.
	private Object numberOfFragments; // : fixed number of fragments in 360 degrees. Values of 3 or more override $fa
										// and $fs
	private boolean addSeparator = false;
	private boolean center;

	public Cylinder(SCAD scad) {
		super(scad);
	}


	@Override
	public Cylinder minimumAngle(Object minimumAngle) {
		this.minimumAngle = minimumAngle;
		return this;
	}
	
	@Override
	public Cylinder minimumCircumferentialLength(Object minimumCircumferentialLength) {
		this.minimumCircumferentialLength = minimumAngle;
		return this;
	}

	@Override
	public Cylinder numberOfFragments(Object numberOfFragments) {
		this.numberOfFragments = numberOfFragments;
		return this;
	}
	
	public Cylinder height(Object height) {
		this.h = height;
		return this;
	}

	public Cylinder radius(Object radius) {
		this.r = radius;
		return this;
	}

	public Cylinder radiusButtom(Object radius) {
		this.r1 = radius;
		return this;
	}
	
	public Cylinder radiusTop(Object radius) {
		this.r2 = radius;
		return this;
	}

	public Cylinder diameter(Object diameter) {
		this.d = diameter;
		return this;
	}


	public Cylinder diameterButtom(Object diameter) {
		this.d1 = diameter;
		return this;
	}


	public Cylinder diameterTop(Object diameter) {
		this.d2 = diameter;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		sb.append("cylinder(");
		append(sb,"h",this.h);
		append(sb,"r",this.r);
		append(sb,"r1",this.r1);
		append(sb,"r2",this.r2);
		append(sb,"d",this.d);
		append(sb,"d1",this.d1);
		append(sb,"d2",this.d2);
		append(sb,"$fa",this.minimumAngle);
		append(sb,"$fs",this.minimumCircumferentialLength);
		append(sb,"$fn",this.numberOfFragments);
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
	public Cylinder center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Cylinder center(boolean value) {
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
