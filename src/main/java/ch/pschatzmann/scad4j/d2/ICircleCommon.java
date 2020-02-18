package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * Some shared functionality for Circles and Cylinders
 * 
 * @author pschatzmann
 *
 */
public interface ICircleCommon {
	public ISCAD minimumAngle(double minimumAngle);

	public ISCAD minimumCircumferentialLength(double minimumCircumferentialLength);

	public ISCAD numberOfFragments(double numberOfFragments);

}