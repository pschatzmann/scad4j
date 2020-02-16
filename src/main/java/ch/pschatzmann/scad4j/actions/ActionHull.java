package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Displays the convex hull of child nodes.
 * 
 * @author pschatzmann
 *
 */
public class ActionHull extends ActionGroup   {
	public ActionHull(ISCAD grp) {
		super(grp);
	}

	@Override
	protected String getCommand() {
		return "hull";
	}

}
