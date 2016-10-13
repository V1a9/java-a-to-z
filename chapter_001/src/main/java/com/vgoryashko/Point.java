package com.vgoryashko;

import java.lang.*;

/** 
 * Class Point to set point coordiantes and calculate distance to another point
 * @author vgoryashko
 * @since 13/10/2016
 * version 1.0
 */

class Point {
	public double x;
	public double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(Point point){
		if (this.x >= 0 && this.y >=0 && point.x >= 0 && point.y >= 0) {
			return Math.sqrt(((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y)));
		} 
		return -1;
	}
}