package com.vgoryashko.trianglearea;

/**
 * Class Triangle to calculate Triangle area built on three points.
 * @author vgoryashko
 * @since 13/10/2016
 * version 1.0
 */

public class Triangle {

	/**
	 * First point.
	 */
	private Point a;

	/**
	 * Second point.
	 */
	private Point b;

	/**
	 * Third point.
	 */
	private Point c;

	/**
	* Constructor for the class.
	* @param value1 - first point for a triangle
	* @param value2 - second point for a triangle
	* @param value3 - second point for a triangle
	*/

	public Triangle(Point value1, Point value2, Point value3) {
		this.a = value1;
		this.b = value2;
		this.c = value3;
	}

	/**
	* Method that calculates area of a triangle.
	* @return double
	*/

	public double area() {
		if (a.distanceTo(b) > 0 && b.distanceTo(c) > 0 && c.distanceTo(a) > 0) {
			double p = (a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a)) / 2;
			return Math.sqrt(p * (p - a.distanceTo(b)) * (p - b.distanceTo(c)) * (p - c.distanceTo(a)));
		}
		return -1;
	}
}