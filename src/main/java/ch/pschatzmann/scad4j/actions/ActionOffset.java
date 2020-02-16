package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

public class ActionOffset extends ActionTransform {
	Double r, delta;

	public ActionOffset(ISCAD obj) {
		super(obj);
	}
	
	public ActionOffset r(double r) {
		this.r = r;
		return this;
	}
	public ActionOffset delta(double delta) {
		this.delta = delta;
		return this;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("offset(");
		if (r!=null) {
			sb.append("r=");
			sb.append(r);
		}
		
		if (r!=null && delta!=null) {
			sb.append(",");
		}
		
		if (delta!=null) {
			sb.append("delta=");
			sb.append(delta);
		}
		sb.append(") ");
		sb.append(obj().toString());
		
	}


}
