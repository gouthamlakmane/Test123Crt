package com.Utils;

public class CallRandomNo {
	public static double setRandomNo(double min, double max) 
	{
		System.out.println("Random value of type double between "+min+" to "+max+ ":"); 
		double a = Math.random()*(max-min+1)+min;
		return a; 
	}

}