package ch.pschatzmann.scad4j;

import org.junit.Test;

import ch.pschatzmann.scad4j.SCAD;
import ch.pschatzmann.scad4j.d1.ISCAD;

/**
 * Unit tests for Actions
 * @author pschatzmann
 *
 */
public class TestActions {

	@Test
	public void test3DSVG() throws Exception {
		SCAD s = new SCAD();
		ISCAD obj = s.union(
			s.difference(
				s.cube().size(3).center(),
				s.sphere().radius(3).center()
			),
			s.intersection(
				s.sphere().radius(1.3).center(),
				s.cube().size(2.1).center()
			)
		).translate().values(0.0, 0.0, 1.5).obj().scale().values(10.0).obj();
		
		System.out.println(obj);
	}

}
