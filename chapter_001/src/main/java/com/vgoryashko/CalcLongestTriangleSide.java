package com.vgoryashko;

/** 
 * Class that calculates max length of triangle's side
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */

public class CalcLongestTriangleSide{
	public double calcLongestSide(Point a, Point b, Point c){
		double maxLength = a.distanceTo(b);
		if (b.distanceTo(c) > a.distanceTo(b)){
			return maxLength = b.distanceTo(c);
		} else if (c.distanceTo(a) > a.distanceTo(b))
			return maxLength = c.distanceTo(a);
			return maxLength;
	}
}