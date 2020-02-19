package ch.pschatzmann.scad4j;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import ch.pschatzmann.scad4j.d1.Comment;
import ch.pschatzmann.scad4j.d1.Echo;
import ch.pschatzmann.scad4j.d1.Group;
import ch.pschatzmann.scad4j.d1.Modules;
import ch.pschatzmann.scad4j.d1.Parameters;
import ch.pschatzmann.scad4j.d2.Circle;
import ch.pschatzmann.scad4j.d2.Ellipse;
import ch.pschatzmann.scad4j.d2.Polygon;
import ch.pschatzmann.scad4j.d2.RegularPolygon;
import ch.pschatzmann.scad4j.d2.Square;
import ch.pschatzmann.scad4j.d2.Text;
import ch.pschatzmann.scad4j.d3.Cube;
import ch.pschatzmann.scad4j.d3.Cylinder;
import ch.pschatzmann.scad4j.d3.Point;
import ch.pschatzmann.scad4j.d3.Polyhedron;
import ch.pschatzmann.scad4j.d3.Sphere;
import ch.pschatzmann.scad4j.format.IFormatter;
import ch.pschatzmann.scad4j.format.Mime;
import ch.pschatzmann.scad4j.format.OpenJSCADFormatter;

/**
 * Factory methods for SCAD objects
 * 
 * A detailed explanation of SCAD can be found at
 * https://en.wikibooks.org/wiki/OpenSCAD_User_Manual/The_OpenSCAD_Language#import
 * 
 * @author pschatzmann
 *
 */
public class SCAD implements Serializable {
	private static IFormatter formatter = new OpenJSCADFormatter();
	private static String VERSION = "V0.1";
	private static boolean isDisply3D = true;
	private Parameters parameters = new Parameters();
	private Modules modules = new Modules(this, this.parameters);
	
	/**
	 * Default Constructor
	 */
	public SCAD() {
	}

	/**
	 * Factory method to create a cube
	 * 
	 * @return Cube
	 */
	public Cube cube() {
		return new Cube(this);
	}


	/**
	 * Factory method to create a Cylinder
	 * 
	 * @return Cylinder
	 */
	public Cylinder cylinder() {
		return new Cylinder(this);
	}

	/**
	 * Factory method to create a Polyhedron
	 * 
	 * @return Polyhedron
	 */
	public Polyhedron polyhedron() {
		return new Polyhedron(this);
	}

	/**
	 * Factory method to create a Sphere
	 * 
	 * @return Sphere
	 */
	public Sphere sphere() {
		return new Sphere(this);
	}

	/**
	 * Factory method to create a Circle
	 * 
	 * @return Circle
	 */
	public Circle circle() {
		return new Circle(this);
	}

	/**
	 * Factory method to create a Ellipse
	 * 
	 * @return Ellipse
	 */
	public Ellipse ellipse() {
		return new Ellipse(this);
	}

	/**
	 * Factory method to create a RegularPolygon
	 * 
	 * @return RegularPolygon
	 */
	public RegularPolygon regularPolygon() {
		return new RegularPolygon(this);
	};

	/**
	 * Factory method to create a Polygon
	 * 
	 * @return Polygon
	 */
	public Polygon polygon() {
		return new Polygon(this);
	};

	/**
	 * Factory method to create a Square
	 * 
	 * @return Square
	 */
	public Square square() {
		return new Square(this);
	};

	/**
	 * Factory method to create a Text
	 * 
	 * @return Text
	 */
	public Text text() {
		return new Text(this);
	};

	/**
	 * Factory method to create a Group of objects
	 * @param obj SCADobjects 
	 * @return Group
	 */
	public Group group(ISCAD... obj) {
		return new Group(this).objects(obj);
	};

	/**
	 * Combined objects with the help of hull(). We apply a hull for
	 * each segment of the indicated objects.
	 * @param obj
	 * @return
	 */
	public ISCAD hull(ISCAD... obj) {
		 Group in = new Group(this).objects(obj);
		 Group result = new Group(this);
		 for (int j=1;j<in.getObjects().size();j++) {
			 Group mg = new Group(this).add(in.getObjects().get(j-1), in.getObjects().get(j));
			 mg.hull();
			 result.add(mg);
		 }		 
		 if (result.getObjects().isEmpty() && !in.getObjects().isEmpty()) {
			 result.add(in.getObjects().get(0));
		 }
		 return result;
	};
	
	/**
	 * Combined objects with the help of hull(). We apply a hull for
	 * each segment of the indicated objects.
	 * @param group
	 * @return
	 */	
	public ISCAD hull(Group group) {
		return this.hull(group.getObjects().toArray(new ISCAD[group.size()]));
	}


	/**
	 * Combined objects with the help of the minkowski() operation
	 * @param obj
	 * @return
	 */
	public ISCAD minkowski(ISCAD... obj) {
		return new Group(this).objects(obj).hull().obj();
	};

	/**
	 * Factory method to create a Echo
	 * @param msg Message to echo
	 * @return SCADObject
	 */
	public ISCAD echo(String msg) {
		return new Echo(this).value(msg);
	};

	/**
	 * Factory method to create a Comment
	 * @param comment  Comment to be written
	 * 
	 * @return SCADObject
	 */
	public ISCAD comment(String comment) {
		return new Comment(this).comment(comment);
	};

	/**
	 * Factory method to create a SCADObject form a file
	 * @param file scad File to be loaded
	 * @param parameters parameters to be changed in the file
	 *  
	 * @return SCADObject
	 */
	public SCADObject scad(File file, Parameters parameters) throws IOException {
		return new SCADObject(this).load(file, parameters);
	};
	/**
	 * 
	 * @param file
	 * @return SCADObject
	 * @throws IOException
	 */
	public SCADObject scad(File file) throws IOException {
		return new SCADObject(this).load(file, this.parameters);		
	}

	/**
	 * Factory method to create a SCADObject form a URL
	 * 
	 * @param url URL to be read to get the scad content
	 * @param parameters parameters to be changed in the file
	 * 
	 * @return SCADObject
	 * @throws IOException
	 */
	public SCADObject scad(URL url, Parameters parameters) throws IOException {
		return new SCADObject(this).load(url, parameters);
	};

	/**
	 * Factory method to create a SCADObject form a URL
	 * 
	 * @param url URL to be read to get the scad content
	 * 
	 * @return SCADObject
	 * @throws IOException
	 */
	public SCADObject scad(URL url) throws IOException {
		return new SCADObject(this).load(url, this.parameters);
	};
	
	/**
	 * Factory method to create a SCADObject from the SCAD commands provided as
	 * string
	 * @param scadCommands 
	 * @return SCADObject
	 */
	public SCADObject scad(String scadCommands) {
		return new SCADObject(this).setSCAD(scadCommands);
	};

	/**
	 * Converts a 2 dimensionsl array to an array of points
	 * 
	 * @param values 2 Dimensional Double array
	 * @return Point array
	 */
	public Point[] points(Double[][] values) {
		Point result[] = new Point[values.length];
		int pos = 0;
		for (Double[] value : values) {
			result[pos] = point(value);
			pos++;
		}
		return result;
	}

	/**
	 * Creates a single point
	 * 
	 * @param values 3Dimensional Double Array
	 * @return Point
	 */
	public Point point(Double[] values) {
		return new Point().value(values);
	}

	/**
	 * Creates a single point
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return Point
	 */
	public Point point(Double x, Double y, Double z) {
		return new Point().value(x, y, z);
	}

	/**
	 * Creates a single 2D point
	 * 
	 * @param x
	 * @param y
	 * @return Point
	 */
	public Point point(Double x, Double y) {
		return new Point().value(x, y, 0.0);
	}

	/**
	 * Union operation for the objects (OR)
	 * 
	 * @param objects SCADObject for union operation
	 * @return SCADObject
	 */
	public ISCAD union(ISCAD... objects) {
		return new Group(this).objects(objects).union().obj();
	}

	/**
	 * Intersection operation on the objects (AND)
	 * 
	 * @param objects SCADObject for intersection operation
	 * @return SCADObject
	 */
	public ISCAD intersection(ISCAD... objects) {
		return new Group(this).objects(objects).intersection().obj();
	}

	/**
	 * Difference operation between the objects
	 * 
	 * @param objects SCADObjects for difference operation
	 * @return SCADObject
	 */
	public ISCAD difference(ISCAD... objects) {
		return new Group(this).objects(objects).difference().obj();
	}
	
	/**
	 * Creates a new Parameters object
	 * @return
	 */
	public Parameters parameters() {
		return new Parameters(this.parameters);
	}

	/**
	 * Adds a value to the global parameters 
	 * @return
	 */
	public Parameters addParameter(String parameter,String value) {
		return this.parameters.add(parameter,value);
	}

	/**
	 * Defines the parameter values
	 * 
	 * @param parameter Parameter name
	 * @param value Parameter value
	 * @return SCAD
	 */
	public SCAD setParameterValue(String parameter, String value) {
		this.parameters.add(parameter, value);
		return this;
	}

	/**
	 * Determines the current formatter implementation
	 * @return
	 */
	public static IFormatter getFormatter() {
		return formatter;
	}

	/**
	 * Defines the current formatter implementation
	 * @param formatter
	 */
	public static void setFormatter(IFormatter formatter) {
		SCAD.formatter = formatter;
	}
	
	/**
	 * Renders the stl object as mime in BeakerX
	 * @param obj
	 * @return
	 */
	public Object display(ISCAD obj) {
		return obj.display();
	}

	/**
	 * Creates an [x,z,z] string
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static String xyz(Double x, Double y, Double z) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(x);
		sb.append(",");
		sb.append(y);
		if (z != null) {
			sb.append(",");
			sb.append(z);
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Displays the content as mime in Jupyter
	 * @param mime
	 * @param content
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	public Object mime(String mime,String content) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Mime.display(mime,content);
	}

	/**
	 * Displays the content of the file as mime in Jupyter
	 * @param mime
	 * @param content
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object mime(String mime,File content) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return Mime.display(mime,content);
	}
	
	
	/**
	 * Displays the content of the file as mime in Jupyter
	 * @param content
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object mime(File content) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return Mime.display(content);
	}
	

	/**
	 * By default the display command is returning a 3d image. 
	 * @return
	 */
	public static boolean isDisplay3D() {
		return isDisply3D ;
	}
	
	/**
	 * Defines if a display is rendering a 3D object or a simple 2D image
	 * @param is3D
	 */
	public static void setDisplay3D(boolean is3D) {
		isDisply3D = is3D;
	}
	
	/**
	 * Factory method for a new Modules object
	 * @return
	 */
	public Modules modules() {
		return this.modules;
	}

	/**
	 * Prints the version information
	 */
	@Override
	public String toString() {
		return   "scad4j " + VERSION;
	}



}
