package com.vgoryashko;

import java.lang.*;

/* Tasks:
 * class Point
 * calculate area of a triangle
 * check coordinates correctness (possibility to calculate area of an triangle)
 */

class Point {
	public double x;
	public double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(Point point){
		if (this.x != point.x && this.y != point.y && this.x >= 0 && this.y >=0 && point.x >= 0 && point.y >= 0) {
			return Math.sqrt(((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y)));
		} 
		return -1;
	}
}

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
		double p = 0;
		if (this.a != null && this.b != null && this.c != null){			
			p = (a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a))/2;
			System.out.println(p);
			return Math.sqrt(p * (p - a.distanceTo(b)) * (p - b.distanceTo(c)) * (p - c.distanceTo(a)));
		}
		return -1;
	}
}

public class CalculatorOfTriangleArea{
	public static void main (String[] args){
		Point a = new Point(100, 100);
		Point b = new Point(100, 100);
		Point c = new Point(50, 35);
		Triangle triangle = new Triangle(a, b, c);
		System.out.println(triangle.area());
	}

}