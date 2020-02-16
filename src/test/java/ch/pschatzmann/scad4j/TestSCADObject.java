package ch.pschatzmann.scad4j;

import org.junit.Assert;
import org.junit.Test;

public class TestSCADObject {
	@Test
	public void testRemove() throws Exception {
		SCADObject o = new SCADObject("test();");
		SCADObject o1 = o.remove("test.*;");
		System.out.println(o1);
		Assert.assertTrue(o1.toString().trim().isEmpty());
	}

}
