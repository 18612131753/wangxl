package gof.ray.Proxy.javaproxy.a2_dongtai_jdk;

public class ProxyTest {

	public static void main(String[] args) {
		// 实例化目标对象
	    UserService userService = new UserServiceImpl();

		// 实例化Invocation
		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

		// 根据目标生成代理对象
		UserService proxy = (UserService) invocationHandler.getProxy();

		// 调用代理对象方法
		String reslut = proxy.add();
		System.out.println(reslut);
	}

}
