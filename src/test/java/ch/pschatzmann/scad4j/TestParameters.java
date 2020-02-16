package ch.pschatzmann.scad4j;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.SCADObject;
import ch.pschatzmann.scad4j.d1.Parameters;

/**
 * Unit test for the parameter management
 * 
 * @author pschatzmann
 *
 */
public class TestParameters {
	@Test
	public void testSimpleNumber() throws Exception {
		Parameters p = new Parameters();
		p.add("test", "1");
		Assert.assertEquals("test=1;", p.resolve("test=123;"));
	}

	@Test
	public void testPrefix() throws Exception {
		Parameters p = new Parameters();
		p.add("test", "1");
		Assert.assertEquals("mytest=123;", p.resolve("mytest=123;"));
	}

	@Test
	public void testSimpleNumberWithSpaces() throws Exception {
		Parameters p = new Parameters();
		p.add("test", "1");
		Assert.assertEquals(" test = 1 ;", p.resolve(" test = 123 ;"));
	}

	@Test
	public void testTextWithSpaces() throws Exception {
		Parameters p = new Parameters();
		p.add("test", "pi");
		Assert.assertEquals(" test = \"pi\" ;", p.resolve(" test = \"text \" ;"));
	}

	@Test
	public void testSimpleNumberWithComment() throws Exception {
		Parameters p = new Parameters();
		p.add("test", "1");
		Assert.assertEquals("test=1; // comment", p.resolve("test=123; // comment"));
	}

	@Test
	public void test$() throws Exception {
		Parameters p = new Parameters();
		p.add("$fn", "50");
		Assert.assertEquals("$fn=50;", p.resolve("$fn=100;"));
	}

	@Test
	public void testEmpty() throws Exception {
		Parameters p = new Parameters();
		p.add("$fn", "50");
		Assert.assertEquals("", p.resolve(""));
	}

	@Test
	public void testNull() throws Exception {
		Parameters p = new Parameters();
		p.add("$fn", "50");
		Assert.assertEquals(null, p.resolve(null));
	}

	@Test
	public void testIntegration() throws Exception {
		SCAD scad = new SCAD();
		Parameters p = new Parameters();
		scad.setParameterValue("part","piZero");
		scad.setParameterValue("$fn","50");
		scad.setParameterValue("header","false");
		SCADObject c = scad.scad(new URL("https://raw.githubusercontent.com/saarbastler/library.scad/master/raspberrypi.scad"),p);
		System.out.println(c);
	}

}
