package ch.pschatzmann.scad4j.d3;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * A polyhedron is the most general 3D primitive solid. It can be used to create
 * any regular or irregular shape including those with concave as well as convex
 * features. Curved surfaces are approximated by a series of flat surfaces.
 * 
 * 
 * @author pschatzmann
 *
 */
public class Polyhedron extends SCAD4JObject {
	private Point[] points;
	private Face faces[];
	private Object convexity;
	boolean addSeparator=false;
	private boolean center;

	public Polyhedron(SCAD scad) {
		super(scad);
	}

	public Polyhedron points(Point... point) {
		this.points = point;
		return this;
	}

	public Polyhedron faces(Face... faces) {
		this.faces = faces;
		return this;

	}

	public Polyhedron convexity(Object convexity) {
		this.convexity = convexity;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		this.addSeparator=false;
		sb.append("polyhedron(");

		if (points != null) {
			append(sb,"points=[",points);
			boolean comma = false;
			for (Point p : points) {
				if (comma) {
					sb.append(",");
				}
				sb.append(p.toString());
				comma = true;
			}
			sb.append("]");
		}
		
		if (faces != null) {
			append(sb,"faces=[",faces);
			boolean comma = false;
			for (Face f : faces) {
				if (comma) {
					sb.append(",");
				}
				sb.append(f.toString());
				comma = true;
			}
			sb.append("]");
		}
		
		if (convexity!=null) {
			append(sb,"convexity=",convexity);
			sb.append(convexity);
		}
		
		sb.append("); ");

	}
	
	private void append(StringBuffer sb, String name, Object value) {
		if (value!=null) {
			if (addSeparator) {
				sb.append(",");
			}
			sb.append(name);
			addSeparator = true;
		}
	}
	
	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Polyhedron center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	public Polyhedron center(boolean value) {
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
