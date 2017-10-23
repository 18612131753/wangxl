package com.ray.proxy.dongtai;

public class ProxyTest {

	public static void main(String[] args) {
		// 实例化目标对象
		UserService userService = new UserServiceImpl();

		// 实例化Invocation
		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

		// 根据目标生成代理对象
		UserService proxy = (UserService) invocationHandler.getProxy();

		// 调用代理对象方法
		proxy.add();
	}

}
