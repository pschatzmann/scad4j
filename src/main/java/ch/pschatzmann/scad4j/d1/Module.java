package ch.pschatzmann.scad4j.d1;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCADObject;

/**
 * Scad Code rendered as module
 * 
 * @author pschatzmann
 *
 */
public class Module {
	private String name;
	private String scadCommand;
	private SCAD scad;

	public Module(SCAD scad, String name, String scadStr) {
		this.setName(name);
		this.scadCommand = scadStr;
		this.scad = scad;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void appendSCAD(StringBuffer sb) {
		appendSCAD(sb, true);
	}

	public void appendSCAD(StringBuffer sb, boolean withEntry) {
		if (scadCommand.indexOf("module") > 0) {
			sb.append(System.lineSeparator());
			sb.append(this.scadCommand);
			sb.append(System.lineSeparator());
		} else {
			sb.append(System.lineSeparator());
			sb.append("module ");
			sb.append(this.getName());
			sb.append("() {");
			sb.append(System.lineSeparator());
			sb.append(this.scadCommand);
			sb.append(System.lineSeparator());
			sb.append("}");
			sb.append(System.lineSeparator());
			if (withEntry) {
				sb.append(name);
				sb.append("();");
				sb.append(System.lineSeparator());
			}
		}
	}

	/**
	 * SCADObject object which represents the module by name (w/o implementation
	 * 
	 * @return
	 */
	public SCADObject ref() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name);
		if (name.indexOf("()") == -1) {
			sb.append("();");
		}
		// create a call to module with the proper name
		SCADObject result = new SCADObject(scad, sb.toString());
		result.setModule(true);
		return result;
	}

	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		this.appendSCAD(sb);
		return sb.toString();
	}

}
