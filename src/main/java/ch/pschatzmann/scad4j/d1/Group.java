package ch.pschatzmann.scad4j.d1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.pschatzmann.scad4j.SCAD4JObject;

/**
 * Represents a collection of 2D or 3D objects
 * 
 * @author pschatzmann
 *
 */
public class Group extends SCAD4JObject {
	private List<ISCAD> objects = new ArrayList();

	/**
	 * Sets the objects in this group
	 * 
	 * @param objects The SCADObject to be grouped
	 * @return Group
	 */
	public Group objects(ISCAD... objects) {
		this.objects.clear();
		this.objects.addAll(Arrays.asList(objects));
		return this;
	}

	/**
	 * Adds the objects to the group
	 * 
	 * @param obj Objects
	 * @return Group
	 */

	public Group add(ISCAD... obj) {
		this.objects.addAll(Arrays.asList(obj));
		return this;
	}

	/**
	 * Returns the currently defined objects
	 * 
	 * @return List of SCADObject
	 */
	public List<ISCAD> getObjects() {
		return this.objects;
	}



	@Override
	public void appendSCAD(StringBuffer sb) {
		appendActions(this.getActions(), sb);
		sb.append(" {");

		for (ISCAD a : objects) {
			sb.append(System.lineSeparator());
			a.appendSCAD(sb);
		}
		sb.append("} ");

	}

}
