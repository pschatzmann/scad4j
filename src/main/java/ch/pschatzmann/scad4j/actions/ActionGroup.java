package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Operation involving multiple objects
 * 
 * @author pschatzmann
 *
 */
public abstract class ActionGroup extends ObjectSourceAction {
	
	ActionGroup(ISCAD grp){
		super(grp);
	}
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append(System.lineSeparator());
		sb.append(getCommand());
		sb.append(" ");
	}
	
	protected abstract String getCommand();

}
