package com.ray.dubbo.javaspi.impl;

import com.ray.dubbo.javaspi.HelloInterface;

public class BHello implements HelloInterface {

	@Override
	public void sayHello() {
		System.out.println( "B say Hello !");
	}

}
