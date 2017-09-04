package com.ray.jvm;

public class StrTest {

	public static void main(String[] args){
		String a= "123";
		String b ="123" ;
		String c = new String("123");
		System.out.println( a== b);
		System.out.println( a== c);		
		System.gc();
	}
}
