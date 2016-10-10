package com.vgoryashko;

public class Calculator{
	private double result;
	
	public void add (double first, double second){
		result = first + second;
	}
	
	public void substract (double first, double second){
		result = first - second;
	}
	
	public void multiply (double first, double second){
		result = first * second;
	}
	
	public void div (double first, double second){
		result = first * second;
	}
	
	public static void main(String[] args){
		Calculator calc = new Calculator();
	}
	
	public double getResult(){
		return result;
	}
	
}