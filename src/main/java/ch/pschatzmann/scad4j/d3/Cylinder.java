package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.d1.ISCAD;
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
	private Double h; // : height of the cylinder or cone
	private Double r; // : radius of cylinder. r1 = r2 = r.
	private Double r1; // : radius, bottom of cone.
	private Double r2; // radius, top of cone.
	private Double d; // : diameter of cylinder. r1 = r2 = d / 2. [Note: Requires version 2014.03]
	private Double d1; // : diameter, bottom of cone. r1 = d1 / 2. [Note: Requires version 2014.03]
	private Double d2; // : diameter, top of cone. r2 = d2 / 2. [Note: Requires version 2014.03]

	private Double minimumAngle; // : minimum angle (in degrees) of each fragment.
	private Double minimumCircumferentialLength; // : minimum circumferential length of each fragment.
	private Double numberOfFragments; // : fixed number of fragments in 360 degrees. Values of 3 or more override $fa
										// and $fs
	private boolean addSeparator = false;

	@Override
	public ISCAD minimumAngle(double minimumAngle) {
		this.minimumAngle = minimumAngle;
		return this;
	}

	@Override
	public ISCAD minimumCircumferentialLength(double minimumCircumferentialLength) {
		this.minimumCircumferentialLength = minimumAngle;
		return this;
	}

	@Override
	public ISCAD numberOfFragments(double numberOfFragments) {
		this.numberOfFragments = numberOfFragments;
		return this;
	}

	public Cylinder height(double height) {
		this.h = height;
		return this;
	}

	public Cylinder radius(double radius) {
		this.r = radius;
		return this;
	}

	public Cylinder radiusButtom(double radius) {
		this.r1 = radius;
		return this;
	}

	public Cylinder radiusTop(double radius) {
		this.r2 = radius;
		return this;
	}

	public Cylinder diameter(double diameter) {
		this.d = diameter;
		return this;
	}

	public Cylinder diameterButtom(double diameter) {
		this.d1 = diameter;
		return this;
	}

	public Cylinder diameterTop(double diameter) {
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
		append(sb,"$fs:",this.minimumCircumferentialLength);
		append(sb,"$fn:",this.numberOfFragments);
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
	
	

}
