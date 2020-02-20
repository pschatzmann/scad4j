package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.format.Utils;

public abstract class ActionTransform extends ObjectSourceAction {
	protected Object x,y,z;
	
	ActionTransform(ISCAD obj){
		super(obj);
	}
	
	public ActionTransform values(Number ...x) {
		return values(Utils.toStringArray(x));
	}

	public ActionTransform values(String...sa) {
		x = y = z = null;
		if (sa.length==1) {
			x = y = z = sa[0];
		} else if (sa.length==2) {
			this.x = sa[0];
			this.y = sa[1];
		} else {
			this.x = sa[0];
			this.y = sa[1];
			this.z = sa[2];
		}
		return this;
	}
	
		
	protected String xyz() {
		return SCAD.xyz(x,y,z);
	}
}
