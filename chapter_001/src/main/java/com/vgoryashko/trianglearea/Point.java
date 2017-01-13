package com.vgoryashko.trianglearea;

/**
 * Class Point to set point coordiantes and calculate distance to another point.
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */

public class Point {

	/**
	 * First coordinate for a point.
	 */
	private double x;

	/**
	 * Second coordinate for a point.
	 */
	private double y;

	/**
	* Constructor for the class.
	* @param value1 - first coordinate for a point
	* @param value2 - second coordinate for a point
	*/

	public Point(double value1, double value2) {
		this.x = value1;
		this.y = value2;
	}

	/**
	* Method that calculates distance between two points.
	* @param point - point
	* @return double
	*/

	public double distanceTo(Point point) {
		return Math.sqrt((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y));
	}
}