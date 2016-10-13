package com.vgoryashko;

import org.junit.Test;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.rules.ExpectedException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** 
 * Class that performs testing of a class com.vgoryashko.CalculatorOfTriangleArea
 * @author vgoryashko
 * @since 10/10/2016
 * version 1.0
 */
 
 public class CalculatorOfTriangleAreaTest{
	
	@Test
	public void testDistanceTo(){
		Point a = new Point(100, 15);
		Point b = new Point(15, 100);
		Point c = new Point(50, 35);
		assertThat(a.distanceTo(b), is(120.20815280171308));
		assertThat(b.distanceTo(c), is(73.824115301167002));
		assertThat(c.distanceTo(a), is(53.851648071345040));
	}
	
	@Test
	public void testAreaCalculation(){
		Triangle triangle = new Triangle(new Point(100, 15), new Point(15, 100), new Point(50, 35));
		assertThat(triangle.area(), is(1275.0000000000005));
	}
 }
