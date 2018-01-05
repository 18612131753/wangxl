package com.ray.base.util;

import java.util.Random;

public class FileNameUtils {

	public static String randomFileName(){
		Random random = new Random( );
		int nTemp = random.nextInt(10000);
		String name = String.valueOf(System.currentTimeMillis())+(nTemp);
		return name ;
	}
}
