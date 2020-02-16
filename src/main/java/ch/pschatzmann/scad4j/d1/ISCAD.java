package ch.pschatzmann.scad4j.d1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Optional;

import ch.pschatzmann.scad4j.actions.ActionColor;
import ch.pschatzmann.scad4j.actions.ActionDifference;
import ch.pschatzmann.scad4j.actions.ActionHull;
import ch.pschatzmann.scad4j.actions.ActionIntersection;
import ch.pschatzmann.scad4j.actions.ActionLinearExtrude;
import ch.pschatzmann.scad4j.actions.ActionMinkowski;
import ch.pschatzmann.scad4j.actions.ActionMirror;
import ch.pschatzmann.scad4j.actions.ActionMultimatrix;
import ch.pschatzmann.scad4j.actions.ActionOffset;
import ch.pschatzmann.scad4j.actions.ActionProjection;
import ch.pschatzmann.scad4j.actions.ActionRender;
import ch.pschatzmann.scad4j.actions.ActionResize;
import ch.pschatzmann.scad4j.actions.ActionRotate;
import ch.pschatzmann.scad4j.actions.ActionScale;
import ch.pschatzmann.scad4j.actions.ActionTranslate;
import ch.pschatzmann.scad4j.actions.ActionUnion;

public interface ISCAD extends Serializable {

	public ISCAD obj();
	

	/**
	 * Saves the object to a SCAD file
	 * 
	 * @param out Output File
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	File save(File out) throws IOException, InterruptedException;

	ActionProjection projection();

	ActionRotate rotate();

	ActionTranslate translate();

	ActionScale scale();

	ActionResize resize();

	ActionMinkowski minkowski();

	ActionHull hull();

	ActionUnion union();

	ActionDifference difference();

	ActionIntersection intersection();

	ActionRender render(double convexity);


	String toSTL() throws IOException, InterruptedException;

	float[][][] to3D() throws IOException, InterruptedException, ParseException;


	ActionLinearExtrude linearExtrude();

	ActionOffset offset();

	ActionColor color();

	ActionMultimatrix multimatrix();

	ActionMirror mirror();

	Optional<String> findExtension(String fileName);

	BufferedImage toPNG() throws IOException, InterruptedException;

	BufferedImage toSVG() throws IOException, InterruptedException;

	BufferedImage toImage(String format) throws IOException, InterruptedException;


	boolean isCenter();

	ISCAD center(boolean value);

	ISCAD center();


	void appendSCAD(StringBuffer sb);

	void addAction(ISCAD objectSourceAction);

	/**
	 * Displays the object as model/stl Mime in BeakerX. We use reflection to
	 * prevent issues if this class is used outside of BeakerX
	 * 
	 * @return mime and stl content
	 */
	Object display();
	
	/**
	 * Displays the object with the OpenSCAD representation
	 * 
	 * @return
	 */
	@Override
	String toString();
}