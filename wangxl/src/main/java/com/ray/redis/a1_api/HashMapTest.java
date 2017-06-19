package com.ray.redis.a1_api;

import java.util.HashMap;

public class HashMapTest {

	public static void main(String[] args) {
		int length = 16 ;
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("a", "value");
		map.put("a1", "value1");
		System.out.println( map.toString() );
		for( int i=0 ;i<50 ;i++){
			int hash = String.valueOf(i).hashCode();
			System.out.println(  hash & (length-1) );
		}
	}

	public int indexFor(int h, int length) {
        return h & (length-1);
    }
}
