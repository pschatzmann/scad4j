package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Creates a circle at the origin.
 * 
 * @author pschatzmann
 *
 */
public class Circle extends SCAD4JObject implements ICircleCommon {
	public Circle(SCAD scad) {
		super(scad);
	}

	private Object radius;
	private Object diameter;
	private Object minimumAngle;
	private Object minimumCircumferentialLength;
	private Object numberOfFragments;
	private boolean comma = false;

	public Circle radius(Object radius) {
		this.radius = radius;
		return this;
	}

	public Circle diameter(Object diameter) {
		this.diameter = diameter;
		return this;
	}

	@Override
	public Circle minimumAngle(Object minimumAngle) {
		this.minimumAngle = minimumAngle;
		return this;
	}

	@Override
	public Circle minimumCircumferentialLength(Object minimumCircumferentialLength) {
		this.minimumCircumferentialLength = minimumAngle;
		return this;
	}

	@Override
	public Circle numberOfFragments(Object numberOfFragments) {
		this.numberOfFragments = numberOfFragments;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		sb.append("circle(");
		appendParameter(sb, "d", diameter);
		appendParameter(sb, "r", radius);
		appendParameter(sb, "$fa", minimumAngle);
		appendParameter(sb, "$fs :", minimumCircumferentialLength);
		appendParameter(sb, "$fn :", numberOfFragments);
		sb.append("); ");
	}

	protected void appendParameter(StringBuffer sb, String string, Object value) {
		if (value != null) {
			if (comma) {
				sb.append(",");
			}
			sb.append(string);
			sb.append("=");
			sb.append(value);
			comma = true;
		}
	}

}
