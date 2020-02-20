package ch.pschatzmann.scad4j;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import ch.pschatzmann.scad4j.format.OpenSCADFormatter;


/**
 * Unit Tests for the file conversion
 * 
 * @author pschatzmann
 *
 */
public class TestFile {
	@Test
	public void test3DSVG() throws Exception {
		SCAD scad = new SCAD();
		//scad.setFormatter(new OpenJSCADFormatterJS());
		ISCAD cube = scad.cube().size(10.0);
		File result = cube.save(new File("testCube.scad"));
		Assert.assertTrue(result.exists());
		result.delete();
	}

	
	
	@Test
	public void testSTL() throws Exception {
		SCAD.setFormatter(new OpenSCADFormatter());
		SCAD scad = new SCAD();
		ISCAD cube = scad.cube().size(10.0);
		System.out.println(cube.toSTL());

	}

}
