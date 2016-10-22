package com.vgoryashko.trianglearea;

/** 
 * Class Triangle to calculate Triangle area built on three points
 * @author vgoryashko
 * @since 13/10/2016
 * version 1.0
 */

import java.lang.*;

class Triangle {
	public Point a;
	public Point b;
	public Point c;
	
	public Triangle(Point a, Point b, Point c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public double area(){
		if (a.distanceTo(b) > 0 && b.distanceTo(c) > 0 && c.distanceTo(a) > 0){
			double p = (a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a))/2;
			return Math.sqrt(p * (p - a.distanceTo(b)) * (p - b.distanceTo(c)) * (p - c.distanceTo(a)));
		}
		return -1;
	}
}