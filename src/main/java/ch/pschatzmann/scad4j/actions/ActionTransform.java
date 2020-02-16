package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.d1.ISCAD;

public abstract class ActionTransform extends ObjectSourceAction {
	protected Double x,y,z;
	
	ActionTransform(ISCAD obj){
		super(obj);
	}
	
	public ActionTransform values(Double x, Double y) {
		return values(x,y,null);
	}
	
	public ActionTransform values(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public ActionTransform values(double v[]) {
		return values(v[0],v[1],v[2]);
	}
		
	protected String xyz() {
		return SCAD.xyz(x,y,z);
	}
}
