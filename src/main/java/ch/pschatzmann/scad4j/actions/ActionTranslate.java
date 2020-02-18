package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionTranslate extends ActionTransform {

	public ActionTranslate(ISCAD obj) {
		super(obj);
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("translate(");
		sb.append(xyz());
		sb.append(") ");
	}

}
