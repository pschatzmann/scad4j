package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCAD4JObject;

public class CommonAction {
	private ISCAD obj;
	
	CommonAction(ISCAD obj){
		this.obj = obj;
	}
	
	public ISCAD obj() {
		return obj;
	}

	public ISCAD toModule(String name) {
		return ((SCAD4JObject)obj).toModule(name);
	};
		
	public SCAD getParent() {
		return this.obj.getParent();
	}
	
	public String toString(boolean withModules) {
		return this.obj.toString(withModules);
	}
	
	public 	ISCAD clear() {
		return this.obj.clear();
	}

	public 	ISCAD copy() {
		return this.obj.copy();
	}

}
