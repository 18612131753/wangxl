package com.ray.dubbo.javaspi;

import java.util.ServiceLoader;

// 文件路径 /resources/META-INF/services/com.ray.dubbo.javaspi.HelloInterface
public class SpiMain {

	public static void main(String[] args) {
		ServiceLoader<HelloInterface> loaders = 
				ServiceLoader.load( HelloInterface.class );
		int i=0 ;
		for( HelloInterface h : loaders ){
			System.out.println( ++i);
			h.sayHello();
		}
	}

}
