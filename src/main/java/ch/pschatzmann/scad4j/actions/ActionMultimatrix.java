package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Multiplies the geometry of all child elements with the given 4x4
 * transformation matrix.
 * 
 * Usage: multmatrix(m = [...]) { ... }
 * 
 * 
 * @author pschatzmann
 *
 */
public class ActionMultimatrix extends ObjectSourceAction {
	double matrix[][];

	public ActionMultimatrix(ISCAD obj) {
		super(obj);
	}

	public ActionMultimatrix values(double matrix[][]) {
		this.matrix = matrix;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("multmatrix(m=[");
		boolean first1 = true;
		for (double a[] : matrix) {
			if (!first1) {
				sb.append(",");
				first1=false;				
			}
			sb.append("[");
			boolean first2 = true;
			for (double d : a) {
				if (!first2) {
					sb.append(",");
					first2=false;
				}
				sb.append(d);
			}
			sb.append("]");
		}
		sb.append(") { ");
		sb.append(obj().toString());
		sb.append("}");
	}


}
