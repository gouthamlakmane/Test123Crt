package com.Utils;

public class CheckRoundOff 
{
	 boolean a1 = true;
	 boolean b1 = false;
	 static boolean testRoundOff = true;
	 static double d;
 public static double compareRoundoffValue(String dataFromexc, String dataFromGui)
 {
	 double  a =Double.parseDouble(dataFromexc);  
	 double  b =Double.parseDouble(dataFromGui);
	 
	 double  difference = a - b;
	 double  value = Math.abs(difference);
	 System.out.println(value);
	 
	 return value;
 }
 
}
