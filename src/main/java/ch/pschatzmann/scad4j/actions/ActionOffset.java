package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionOffset extends ActionTransform {
	Object r, delta;

	public ActionOffset(ISCAD obj) {
		super(obj);
	}
	
	public ActionOffset r(Object r) {
		this.r = r;
		return this;
	}
	public ActionOffset delta(Object delta) {
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
