package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * minkowski() operation on multiple obects
 * @author pschatzmann
 *
 */
public class ActionMinkowski extends ActionGroup  {
	public ActionMinkowski(ISCAD grp) {
		super(grp);
	}

	@Override
	protected String getCommand() {
		return "minkowski()";
	}

}
