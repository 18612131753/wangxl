package com.ray.redis.a1_api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 模拟随机key，看看hash的分布
 * */
public class HashTest {

	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Random random = new Random( );
		for(int i=0;i<10000;i++){
			//Math.abs 绝对值
			Integer it = Math.abs(random.nextInt(40))  ;
			String s = HashTest.getRandomString( it );
			int hashs = Math.abs(s.hashCode()) ;
			System.out.println( s + "  " +hashs );
			Integer hash_s = hashs % 3 ;
			if(map.get( hash_s ) == null ){
				map.put( hash_s , 1 );
			} else {
				map.put( hash_s  ,map.get( hash_s )+ 1 );
			}
		}
		System.out.println( map.toString() );
	}

	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     
}
