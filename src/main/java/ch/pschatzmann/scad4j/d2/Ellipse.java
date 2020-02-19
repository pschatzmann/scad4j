package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;

/**
 * An ellipse can be created from a circle by using either scale() or resize()
 * to make the x and y dimensions unequal. See OpenSCAD User
 * Manual/Transformations
 * 
 * 
 * @author pschatzmann
 *
 */
public class Ellipse extends Circle {
	
	public Ellipse(SCAD scad) {
		super(scad);
	}

	public Ellipse size(double x, double y) {
		this.diameter(x + y / 2);
		return (Ellipse) this.resize().values(x, y).obj();
	}
}
