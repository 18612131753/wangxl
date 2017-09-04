package com.ray.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * jvm配置：-Xmx30m -Xms30m -Xmn20m -Xloggc:D:/boxlog/gc/gc.log -XX:+PrintGCDetails -XX:+PrintGC
 * */
public class Test {

	public static void main(String[] args) {
		byte[] b = null;
		for (int i = 0; i < 2; i++)
			b = new byte[1 * 1024 * 1024];
		System.out.println(b);
		String a1 = "abc";
		
		String a2 = "abc";
		System.out.println(a1 == a2);
		List list = new ArrayList<HashMap>();
		for (int i = 0; i < 800000; i++) {
			list.add(new HashMap());
			System.out.println(i);
		}
	}
}