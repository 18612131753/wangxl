package gof.ray.Proxy.javaproxy.a2_dongtai_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义简单的Invocation，对接口提供的方法进行增强
 * 
 * 动态代理类只能代理接口（不支持抽象类），代理类都需要实现InvocationHandler类，实现invoke方法。
 * 该invoke方法就是调用被代理接口的所有方法时需要调用的，该invoke方法返回的值是被代理接口的一个实现类
 */
public class MyInvocationHandler implements InvocationHandler {

	// 目标对象
	private Object target;

	/**
	 * 构造方法
	 */
	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	/**
	 * 执行目标对象的方法 关联的这个实现类的方法被调用时将被执行
	 * InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数
	 */
	@Override  
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// 在目标方法执行前简单打印一下
		System.out.println("代理：----------before----------");
		
		// 执行目标方法对象
		Object result = method.invoke(target, args);

		// 在目标方法执行之后简单打印一下
		System.out.println("代理：----------after----------");

		return result;
	}

	/**
	 * 自定义方法获取目标对象的代理对象
	 * 
	 * @return 代理对象
	 */
	public Object getProxy() {
		// 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
		// 第一个参数：被代理的类的类加载器，需要将其指定为和目标对象同一个类加载器
		// 第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
		// 第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
		// 根据传入的目标返回一个代理对象
		return Proxy.newProxyInstance(
				this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(), 
				this);
	}

}
