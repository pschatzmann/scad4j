package ch.pschatzmann.scad4j.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Management of source object
 * @author pschatzmann
 *
 */
public abstract class ObjectSourceAction implements ISCAD {
	private ISCAD obj;
	
	public ObjectSourceAction(ISCAD obj){
		this.obj = obj.obj();
		this.obj.addAction(this);
	}

	@Override
	public ISCAD obj() {
		return obj;
	}
	
	@Override
	public String toString() {
		return obj().toString();
	}

	@Override
	public ActionProjection projection() {
		return obj().projection();
	}

	@Override
	public ActionRotate rotate() {
		return obj().rotate();
	}

	@Override
	public ActionTranslate translate() {
		return obj().translate();
	}

	@Override
	public ActionScale scale() {
		return obj().scale();
	}

	@Override
	public ActionResize resize() {
		return obj().resize();
	}

	@Override
	public ActionMirror mirror() {
		return obj().mirror();
	}

	@Override
	public ActionMultimatrix multimatrix() {
		return obj().multimatrix();
	}

	@Override
	public ActionColor color() {
		return obj().color();
	}

	@Override
	public ActionOffset offset() {
		return obj().offset();
	}

	@Override
	public Object display() {
		return obj().display();
	}

	@Override
	public File save(File file) throws IOException, InterruptedException {
		return obj().save(file);
	}

	@Override
	public ActionMinkowski minkowski() {
		return obj().minkowski();
	}

	@Override
	public ActionHull hull() {
		return obj().hull();
	}

	@Override
	public ActionUnion union() {
		return obj().union();
	}

	@Override
	public ActionDifference difference() {
		return obj().difference();
	}

	@Override
	public ActionIntersection intersection() {
		return obj().intersection();
	}

	@Override
	public ActionRender render(double convexity) {
		return obj().render(convexity);
	}

	@Override
	public void appendSCAD(StringBuffer sb) {		
		throw new RuntimeException("This must be overwritten");
	}


	@Override
	public String toSTL() throws IOException, InterruptedException {
		return obj().toSTL();
	}

	@Override
	public float[][][] to3D() throws IOException, InterruptedException, ParseException {
		return obj().to3D();
	}

	@Override
	public ActionLinearExtrude linearExtrude() {
		return obj().linearExtrude();
	}

	@Override
	public Optional<String> findExtension(String fileName) {
		return obj().findExtension(fileName);
	}

	@Override
	public BufferedImage toPNG() throws IOException, InterruptedException {
		return obj().toPNG();
	}

	@Override
	public BufferedImage toSVG() throws IOException, InterruptedException {
		return obj().toSVG();
	}

	@Override
	public BufferedImage toImage(String format) throws IOException, InterruptedException {
		return obj().toImage(format);
	}

	@Override
	public boolean isCenter() {
		return obj().isCenter();
	}

	@Override
	public ISCAD center(boolean value) {
		return obj().center(value);
	}

	@Override
	public ISCAD center() {
		return obj().center();
	}

	@Override
	public void addAction(ISCAD objectSourceAction) {
		throw new RuntimeException("Not supported");
		
	}

}
