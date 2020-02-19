package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;
import ch.pschatzmann.scad4j.d3.Point;

/**
 * Creates a multiple sided shape from a list of x,y coordinates. A polygon is
 * the most powerful 2D object. It can create anything that circle and squares
 * can, as well as much more. This includes irregular shapes with both concave
 * and convex edges. In addition it can place holes within that shape.
 * 
 * 
 * @author pschatzmann
 *
 */
public class Polygon extends SCAD4JObject {
	private Point points[];
	private Integer paths[][];
	private Integer convexity = 1;

	public Polygon(SCAD scad) {
		super(scad);
	}

	Polygon points(Point... points) {
		this.points = points;
		return this;
	}

	Polygon path(Integer path[][]) {
		this.paths = path;
		return this;
	}

	Polygon convexity(Integer convexity) {
		this.convexity = convexity;
		return this;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(),sb);

		sb.append("polygon(points = [");
		boolean first = true;
		for (Point p : points) {
			if (!first) {
				sb.append(",");
			}
			sb.append(p);
			first = false;
		}
		sb.append("]");
		
		if (paths!=null) {
			sb.append(",");
			sb.append("paths=[");
			boolean first1 = true;
			for (Integer path[] : paths) {
				if (!first1) {
					sb.append("],[");	
				}
				boolean first2 = true;
				sb.append("[");
				for (Integer value : path) {
					if (!first2) {
						sb.append(",");						
					}
					sb.append(value);
					first2 = false;
				}
				first1 = false;	
				sb.append("]");
			}
			sb.append("]");
		}

		if (convexity != null) {
			sb.append(",");
			sb.append("convexity=");
			sb.append(convexity);
		}
		sb.append("); ");

	}

}
