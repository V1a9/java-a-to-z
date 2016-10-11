package com.vgoryashko;

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
		
		return ((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y))/2;
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
		
		return -1;
	}
}

public class CalculatorOfTriangleArea{
	public static void main (String[] args){
		Point a = new Point(1,3);
		Point b = new Point(2, 5);
		System.out.println(a.distanceTo(b));
	}

}