package com.ray.dubbo.javaspi.impl;

import com.ray.dubbo.javaspi.HelloInterface;

public class AHello implements HelloInterface {

	@Override
	public void sayHello() {
		System.out.println( "A say Hello !");
	}

}
