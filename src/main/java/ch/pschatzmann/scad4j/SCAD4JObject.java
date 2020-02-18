package ch.pschatzmann.scad4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

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
import ch.pschatzmann.scad4j.format.IFormatter;
import ch.pschatzmann.scad4j.format.Mime;
import ch.pschatzmann.scad4j.format.OpenJSCADFormatter;
import ch.pschatzmann.scad4j.format.OpenSCADFormatter;
import ch.pschatzmann.scad4j.format.Utils;
import ch.pschatzmann.scad4j.mesh.Mesh;
import ch.pschatzmann.scad4j.mesh.STL;

/**
 * Common functionality for all SCAD objects
 * 
 * @author pschatzmann
 *
 */
public class SCAD4JObject implements ISCAD {
	boolean center = false;
	List<ISCAD> actions = new ArrayList();
	
	/**
	 * Centers the object (center = true)
	 * @return SCADObject
	 */
	@Override
	public ISCAD center() {
		center = true;
		return this;
	}

	/**
	 * Defines if the object is centered
	 * @param value true if object should be centered
	 * @return SCADObject
	 */
	@Override
	public ISCAD center(boolean value) {
		center = value;
		return this;
	}

	/**
	 * Determines if the object is centered
	 * @return true if it is centered
	 */
	@Override
	public boolean isCenter() {
		return center;
	}


	/**
	 * Method to construct the SCAD representation which is finally provided by the
	 * toString()
	 * @param sb StringBuffer which collects the result
	 */
	@Override
	public void appendSCAD(StringBuffer sb) {}

	
	/**
	 * Converts the object to an image
	 * @param format (svg, png)
	 * @return BufferedImage
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@Override
	public BufferedImage toImage(String format) throws IOException, InterruptedException {
		IFormatter f = SCAD.getFormatter();
		InputStream is = f.formatToStream(this,format);
		BufferedImage image = ImageIO.read(is);	
		is.close();
		return image;
	}
	
	/**
	 * Converts the object to a SVG image
	 * @return BufferedImage
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public BufferedImage toSVG() throws IOException, InterruptedException {
		return toImage("svg");
	}
	
	/**
	 * Converts the object to a PNG image
	 * @return Image as png 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public BufferedImage toPNG() throws IOException, InterruptedException {
		return toImage("png");
	}
	
	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#save(java.io.File)
	 */

	@Override
	public File save(File out) throws IOException, InterruptedException {
		Optional<String> extension = findExtension(out.getName());
		if (extension.isPresent()) {
			String format = extension.get();
			IFormatter f = SCAD.getFormatter();
			String scad = "scad";
			if (format.equalsIgnoreCase(scad)) {
				 Utils.write(this.toString(), out);
				
			} else {
				 f.formatToFile(this,format, out);		
			}
			return out;
		} else {
			throw new IOException("You must specifiy a file with a valid extension");
		}
	}
	
	/**
	 * Determines the extension of the file name
	 * @param fileName
	 * @return File Extension
	 */
	@Override
	public Optional<String> findExtension(String fileName) {
	    int lastIndex = fileName.lastIndexOf('.');
	    if (lastIndex == -1) {
	        return Optional.empty();
	    }
	    return Optional.of(fileName.substring(lastIndex + 1));
	}
	
	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#projection()
	 */
	@Override
	public ActionProjection projection() {
		return new ActionProjection(this);
	}
	
	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#rotate()
	 */
	@Override
	public ActionRotate rotate() {
		return new ActionRotate(this);
	}
	
	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#translate()
	 */
	@Override
	public ActionTranslate translate() {
		return new ActionTranslate(this);
	}

	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#scale()
	 */
	@Override
	public ActionScale scale() {
		return new ActionScale(this);
	}
	
	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#resize()
	 */
	@Override
	public ActionResize resize() {
		return new ActionResize(this);
	}

	@Override
	public ActionMirror mirror() {
		return new ActionMirror(this);
	}

	@Override
	public ActionMultimatrix multimatrix() {
		return new ActionMultimatrix(this);
	}

	@Override
	public ActionColor color() {
		return new ActionColor(this);
	}

	@Override
	public ActionOffset offset() {
		return new ActionOffset(this);
	}
	
	
	@Override
	public ActionLinearExtrude linearExtrude() {
		return new ActionLinearExtrude(this);
	}

	/**
	 * Performs a minkowski operation
	 * 
	 * @return ActionMinkowski
	 */
	@Override
	public ActionMinkowski minkowski() {
		return new ActionMinkowski(this);
	}

	/**
	 * Performs a hull operation
	 * 
	 * @return ActionHull
	 */
	@Override
	public ActionHull hull() {
		return new ActionHull(this);
	}

	/**
	 * Performs a union operation
	 * 
	 * @return ActionUnion
	 */
	@Override
	public ActionUnion union() {
		return new ActionUnion(this);
	}

	/**
	 * Performs a difference operation
	 * 
	 * @return ActionDifference
	 */
	@Override
	public ActionDifference difference() {
		return new ActionDifference(this);
	}

	/**
	 * Performs a intersection operation
	 * 
	 * @return ActionIntersection
	 */
	@Override
	public ActionIntersection intersection() {
		return new ActionIntersection(this);
	}

	/**
	 * Performs a render operation
	 * 
	 * @param convexity convexity
	 * 
	 * @return ActionRender
	 */
	@Override
	public ActionRender render(double convexity) {
		return new ActionRender(this);
	}
	/**
	 * Creates a temporay stl file and converts it to a 3D array.
	 * @return float[][][]
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@Override
	public float[][][] to3D() throws IOException, InterruptedException, ParseException {
		File tmp = Utils.createTempFile("stl");
		this.save(tmp);
		return STL.to3D(tmp);
	}
	
	public Mesh toMesh() throws IOException, ParseException, InterruptedException {
		File tmp = Utils.createTempFile("stl");
		this.save(tmp);
		return STL.toMesh(tmp);
	}

	@Override
	public String toSTL() throws IOException, InterruptedException {
		IFormatter f = SCAD.getFormatter();
		File stl = Utils.createTempFile("stl");
		f.formatToFile(this,"stl",stl);
		
		return Utils.textFile2String(stl);
	}
	

	@Override
	public void addAction(ISCAD action) {
		this.actions.add(0, action);
	}
	
	
	public List<ISCAD> getActions(){
		return this.actions;
	}

	/* (non-Javadoc)
	 * @see ch.pschatzmann.scadjvm.ICommonActions#display()
	 */
	@Override
	public Object display() {
		try {
			return SCAD.isDisplay3D() ? Mime.display("model/stl", this.toSTL()) : this.toPNG();
		} catch (ClassNotFoundException ex) {
			System.err.println(ex.getMessage());
			return "This method is only supported in BeakerX";
		} catch (Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	
	/**
	 * Implementation of ObjectSource Interface
	 * @return
	 */
	@Override
	public ISCAD obj() {
		return this;
	}

	public void appendActions(List<ISCAD> actions, StringBuffer sb) {
		actions.forEach(a -> a.appendSCAD(sb));
	};
	
	/**
	 * Serializes the Object into SCAD commands
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		appendSCAD(sb);
		return sb.toString();
	}


}
