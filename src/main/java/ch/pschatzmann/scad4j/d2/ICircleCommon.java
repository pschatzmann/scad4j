package ch.pschatzmann.scad4j.d2;

import ch.pschatzmann.scad4j.ISCAD;

/**
 * Some shared functionality for Circles and Cylinders
 * 
 * @author pschatzmann
 *
 */
public interface ICircleCommon {
	public ISCAD minimumAngle(Object minimumAngle);

	public ISCAD minimumCircumferentialLength(Object minimumCircumferentialLength);

	public ISCAD numberOfFragments(Object numberOfFragments);


}