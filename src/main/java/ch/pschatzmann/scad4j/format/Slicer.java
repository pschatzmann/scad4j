package ch.pschatzmann.scad4j.format;

import java.io.File;

/**
 * slic3r [ ACTION ] [ OPTIONS ] [ model1.stl model2.stl ... ]
 * TODO
 * @author pschatzmann
 *
 */
public class Slicer {
	//public enum ExportFormat {gcode,stml, amf, "3mf", obj, pov, svg, sla}
	private boolean split = true;
	private boolean repair = true;
	private int scale = 100;
	private String format = "gcode";
	
	public void slice(File stlFile) {
		
	}

	public boolean isSplit() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}

	public boolean isRepair() {
		return repair;
	}

	public void setRepair(boolean repair) {
		this.repair = repair;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getFormat() {
		return format;
	}

	/**
	 * Defines the export format
	 * @param format gcode,stml, amf, "3mf", obj, pov, svg, sla
	 */
	public void setFormat(String format) {
		this.format = format;
	}

}
