package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * A regular polygon of 3 or more sides can be created by using circle() with
 * $fn set to the number of sides. The following two pieces of code are
 * equivalent.
 * 
 * 
 * @author pschatzmann
 *
 */
public class RegularPolygon extends SCAD4JObject {
	private Object radius;
	private Object numberOfSides = "5";

	public RegularPolygon(SCAD scad) {
		super(scad);
	}

	public RegularPolygon radius(Object radius) {
		this.radius = radius;
		return this;
	}

	public RegularPolygon numberOfSides(Object numberOfSides) {
		this.numberOfSides = numberOfSides;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);
		new Circle(this.getParent()).radius(radius).numberOfFragments(numberOfSides).appendSCAD(sb);
	}

}
