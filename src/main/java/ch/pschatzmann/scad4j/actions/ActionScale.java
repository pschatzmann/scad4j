package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionScale extends ActionTransform {

	public ActionScale(ISCAD obj) {
		super(obj);
	}
	
	public ActionScale values(Number scale) {
		return (ActionScale)this.values(scale,scale,scale);
	}
	public ActionScale values(String scale) {
		return (ActionScale)this.values(scale,scale,scale);
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("scale(");
		sb.append(xyz());
		sb.append(") ");
	}

}
