package ch.pschatzmann.scad4j.d1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import ch.pschatzmann.scad4j.ISCAD;
import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCADObject;
import ch.pschatzmann.scad4j.format.Utils;

/**
 * Management of multiple modules
 * 
 * @author pschatzmann
 *
 */
public class Modules {
	private Map<String, Module> map = new TreeMap();
	private StringBuffer scad = null;
	private Parameters parameters;
	private SCAD scadRef;

	public Modules(SCAD ref, Parameters parameters) {
		this.parameters = parameters;
		this.scadRef = ref;
	}

	/**
	 * Adds a new module
	 * 
	 * @param iscad
	 * @param name
	 * @return
	 */
	public Modules add(String name, ISCAD iscad) {
		map.put(name, new Module(scadRef, name, iscad.obj().toString(false)));
		scad = null;
		return this;
	}

	/**
	 * Gets an existing module
	 * 
	 * @param name
	 * @return
	 */
	public Module get(String name) {
		return map.get(name);
	}
	
	/**
	 * Returns the reference to the Scad Object that can be used for futher transformations
	 * @param name
	 * @return
	 */
	public SCADObject ref(String name) {
		Module m = this.get(name);
		if (m==null) throw new RuntimeException("Module '"+name+"' does not exist");
		SCADObject result = m.ref();
		result.setName(name);
		return result;
	}

	/**
	 * Returns the module names
	 * 
	 * @return
	 */
	public Collection<String> list() {
		return map.keySet();
	}

	/**
	 * Displays a module
	 * 
	 * @param name
	 * @return
	 */
	public Object display(String name) {
		SCADObject obj = obj(name);
		return obj.display();
	}

	public SCADObject obj() {
		return obj(null);
	}

	
	public SCADObject obj(String name) {
		StringBuffer sb = new StringBuffer();
		appendSCAD(name, sb);
		SCADObject obj = new SCADObject(scadRef, Utils.format(sb.toString()));
		return obj;
	}

	public void appendSCAD(String name, StringBuffer sb) {
		sb.append(getScadString());
		sb.append(System.lineSeparator());
		if (name != null) {
			sb.append(name);
			if (name.indexOf("()") == -1) {
				sb.append("();");
			}
			sb.append(System.lineSeparator());
		}
	}

	public File save(String name, File out) throws IOException, InterruptedException {
		SCADObject obj = obj(name);
		return obj.save(out);
	}

	public BufferedImage toImage(String format, String name) throws IOException, InterruptedException {
		return obj(name).toImage(format);
	}

	public BufferedImage toSVG(String name) throws IOException, InterruptedException {
		return obj(name).toSVG();
	}

	public BufferedImage toPNG(String name) throws IOException, InterruptedException {
		return obj(name).toPNG();
	}

	public String toString() {
		return Utils.format(getScadString().toString());
	}

	public StringBuffer getScadString() {
		if (scad == null) {
			scad = new StringBuffer();

			// add parameters
			for (String name : this.parameters.getKeys()) {
				scad.append(System.lineSeparator());
				scad.append(name);
				scad.append(" = ");
				scad.append(parameters.getValue(name));
				scad.append(";");
				scad.append(System.lineSeparator());
			}
			scad.append(System.lineSeparator());

			// add modules
			for (Module module : map.values()) {
				module.appendSCAD(scad, false);
			}
		}
		return scad;
	}

}
