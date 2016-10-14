package com.vgoryashko;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** 
 * Class that performs testing of a class com.vgoryashko.Triangle
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */
 
 public class TriangleTest{
	
	@Test
	public void testAreaCalculation(){
		Triangle triangle = new Triangle(new Point(100, 15), new Point(15, 100), new Point(50, 35));
		assertThat(triangle.area(), is(1275.0000000000005));
	}
 }
