package com.vgoryashko;

/** 
 * Class that performs testing of a class com.vgoryashko.CalcLongestTriangleSide
 * @author vgoryashko
 * @since 13/10/2016
 * version 1.0
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalcLongestTriangleSideTest{
	
	@Test
	public void calcLongestSideTest(){
		Point a = new Point(100, 15);
		Point b = new Point(15, 100);
		Point c = new Point(50, 35);
		CalcLongestTriangleSide calcLongest = new CalcLongestTriangleSide();
		assertThat(calcLongest.calcLongestSide(a, b, c), is(120.20815280171308));
	}
}