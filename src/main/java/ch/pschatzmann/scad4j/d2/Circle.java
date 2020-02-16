package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Creates a circle at the origin.
 * 
 * @author pschatzmann
 *
 */
public class Circle extends SCAD4JObject implements ICircleCommon {
	private Double radius;
	private Double diameter;
	private Double minimumAngle;
	private Double minimumCircumferentialLength;
	private Double numberOfFragments;
	private boolean comma = false;

	public Circle radius(double radius) {
		this.radius = radius;
		return this;
	}

	public Circle diameter(double diameter) {
		this.diameter = diameter;
		return this;
	}

	@Override
	public Circle minimumAngle(double minimumAngle) {
		this.minimumAngle = minimumAngle;
		return this;
	}

	@Override
	public Circle minimumCircumferentialLength(double minimumCircumferentialLength) {
		this.minimumCircumferentialLength = minimumAngle;
		return this;
	}

	@Override
	public Circle numberOfFragments(double numberOfFragments) {
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

	protected void appendParameter(StringBuffer sb, String string, Double value) {
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
