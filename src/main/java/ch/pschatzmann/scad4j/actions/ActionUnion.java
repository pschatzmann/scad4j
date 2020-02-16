package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

public class ActionUnion extends ActionGroup {
	
	public ActionUnion(ISCAD grp) {
		super(grp);
	}

	@Override
	protected String getCommand() {
		return "union()";
	}


}
