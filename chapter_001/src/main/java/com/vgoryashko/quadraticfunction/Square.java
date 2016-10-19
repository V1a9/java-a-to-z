package com.vgoryashko;

/* 
 * Class com.vgoryashko.quadraticfunction.Square
 * @author vgoryashko
 * @since 19/10/2016
 * @version 1.1
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
				System.out.print(calculate(j) + ", ");
			} else
				System.out.print(calculate(j) + ".");
		}
	}
}