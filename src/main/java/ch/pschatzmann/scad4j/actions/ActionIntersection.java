package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * intersection() on objects
 * 
 * @author pschatzmann
 *
 */
public class ActionIntersection extends ActionGroup {
	public ActionIntersection(ISCAD grp) {
		super(grp);
	}

	@Override
	protected String getCommand() {
		return "intersection()";
	}

}
