package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionInclude extends ObjectSourceAction   {
	ISCAD incl;
	
	public ActionInclude(ISCAD obj, ISCAD incl) {
		super(obj);
		this.incl = incl;
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append(incl.toString());
	}





}
