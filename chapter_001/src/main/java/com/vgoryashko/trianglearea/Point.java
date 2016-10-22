package com.vgoryashko.trianglearea;

/** 
 * Class Point to set point coordiantes and calculate distance to another point
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */

import java.lang.*;

class Point {
	public double x;
	public double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(Point point){
		return Math.sqrt(((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y)));
	}
}