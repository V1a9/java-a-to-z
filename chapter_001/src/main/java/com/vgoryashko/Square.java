package com.vgoryashko;

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
		//float discr = pow(this.b, 2) - 4*this.a*this.c;
		return (float)(this.a * Math.pow(x, 2) + this.b * x + c);
	}
	
	public void show(int start, int finish, int step){
		//int size;
		int index = 0;
		/*while (start <= finish){
			start += step;
			size++;
		}*/
		float[] result = new float[6];
		for (int j = -3; j <= 2; j++){
			if (index < 6){
				result[index++] = calculate(j);
			}
		}
		System.out.print(Arrays.toString(result));
	}
}