package com.vgoryashko;

/** 
 * Class that performs testing of a class com.vgoryashko.CalcLongestSide
 * @author vgoryashko
 * @since 15/10/2016
 * version 1.2
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalcLongestSideTest{
	
	@Test
	public void calcLongestSideTest(){
		Point a = new Point(100, 15);
		Point b = new Point(15, 100);
		Point c = new Point(50, 35);
		CalcLongestSide calcLongest = new CalcLongestSide();
		assertThat(calcLongest.calcLongestSide(a.distanceTo(b), b.distanceTo(c), c.distanceTo(a)), is(120.20815280171308));
	}
}