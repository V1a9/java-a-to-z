package com.vgoryashko;

/* 
 * Class com.vgoryashko.quadraticfunction.Square
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.2
 */

import java.lang.*;
import java.util.*;

public class Square{
	private int a;
	private int b;
	private int c;
	
	Square(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public float calculate(int x){
		return (float)(this.a * Math.pow(x, 2) + this.b * x + c);
	}
	
	public void show(int start, int finish, int step){
		for (int j = start; j <= finish; j += step){
			if (j < finish) {
				//System.out.format("%.1f%c%c", calculate(j), '\u002c', '\u0020');
				System.out.format("%.1f, ", calculate(j));
			} else
				//System.out.format("%.1f%c", calculate(j), '\u002E');
				System.out.format("%.1f.", calculate(j));
		}
	}
}