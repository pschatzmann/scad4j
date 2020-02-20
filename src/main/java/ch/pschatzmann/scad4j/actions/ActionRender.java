package ch.pschatzmann.scad4j.actions;

import ch.pschatzmann.scad4j.ISCAD;

public class ActionRender extends ObjectSourceAction {
	private ISCAD obj;
	private Object convexity;
	
	public ActionRender(ISCAD group) {
		super(group);
	}

	public ISCAD values(Object convexity) {
		this.convexity = convexity;
		return obj;
	}

	@Override
	public void appendSCAD(StringBuffer sb) {
		sb.append("render(");
		if (convexity!=null) {
			sb.append(convexity);
		}
		sb.append(")");
	}

	@Override
	public ISCAD obj() {
		return obj;
	}
}
