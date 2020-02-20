package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

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
	private Object matrix[][];

	public ActionMultimatrix(ISCAD obj) {
		super(obj);
	}

	public ActionMultimatrix values(Object matrix[][]) {
		this.matrix = matrix;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("multmatrix(m=[");
		boolean first1 = true;
		for (Object a[] : matrix) {
			if (!first1) {
				sb.append(",");
				first1=false;				
			}
			sb.append("[");
			boolean first2 = true;
			for (Object d : a) {
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
