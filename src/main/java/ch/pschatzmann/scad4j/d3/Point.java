package ch.pschatzmann.scad4j.d3;

import java.io.Serializable;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.format.Utils;

public class Point implements Serializable {
	private Object x,y,z;

	public Point() {
	}

	public Point value(Number ... values) {
		return value(Utils.toStringArray(values));
	}

	public Point value(String ... values) {
		switch(values.length) {
		case 1:
			return value(values[0],"0.0",null);
		case 2:
			return value(values[0],values[1],null);
		case 3:
			return value(values[0],values[1],values[2]);
		default:
			return value("0.0","0.0",null);
		}
	}

	public Point value(String x, String y, String z) {
		this.x = x; 
		this.y = y;
		this.z = z;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(x);
		sb.append(",");
		sb.append(y);
		// z is optional
		if (z!=null) {
			sb.append(",");
			sb.append(z);
		}
		return sb.toString();
	}
}
