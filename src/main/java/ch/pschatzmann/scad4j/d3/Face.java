package ch.pschatzmann.scad4j.d3;

import java.io.Serializable;

/**
 * Utility class to create and represent a Face
 * 
 * @author pschatzmann
 *
 */
public class Face implements Serializable {
	private Object[][] faces;

	public Face faces(Object[][] faces) {
		this.faces = faces;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean comma = false;
		for (Object a[] : faces) {
			if (comma) {
				sb.append(",");
			}
			sb.append(toString(a));
			comma = true;
		}
		sb.append("]");
		return sb.toString();
	}

	private String toString(Object[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean comma = false;
		for (Object value : array) {
			if (comma) {
				sb.append(",");
			}
			sb.append(value);
			comma = true;
		}
		sb.append("]");
		return null;
	}
}
