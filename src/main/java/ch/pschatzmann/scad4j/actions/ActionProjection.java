package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionProjection extends ObjectSourceAction{
	private Boolean cut;

	public ActionProjection(ISCAD obj){
		super(obj);
	}
	
	public ActionProjection value(boolean cut) {
		this.cut = cut;
		return this;
	} 
	
	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("projection(");
		if (cut!=null) {
			sb.append("cut=");
			sb.append(cut);
		}
		sb.append(")");
		sb.append(" ");
	}
	

}
