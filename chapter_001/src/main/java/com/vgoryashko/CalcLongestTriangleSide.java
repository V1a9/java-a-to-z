package com.vgoryashko;

/** 
 * Class that calculates max length of triangle's side
 * @author vgoryashko
 * @since 13/10/2016
 * version 1.0
 */

public class CalcLongestTriangleSide{
	public double calcLongestSide(Point a, Point b, Point c){
		double maxLength = 0;
		double[] lengths = new double[]{a.distanceTo(b), b.distanceTo(c), c.distanceTo(a)};
		for(double length : lengths){
			if (length >= maxLength)
				maxLength = length;
		}
		return maxLength;
	}
}