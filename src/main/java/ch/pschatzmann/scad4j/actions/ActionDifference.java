package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * difference() on objects
 * 
 * @author pschatzmann
 *
 */
public class ActionDifference extends ActionGroup  {
	
	public ActionDifference(ISCAD obj){
		super(obj);
	}
	
	@Override
	protected String getCommand(){
		return "difference()";
	}

}
