package gof.ray.Proxy.javaproxy.a3_dongtai_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        // 设置创建子类的类(为哪个类设置代理类)
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * intercept 拦截，拦截所有目标类方法的调用
     *  Object为由CGLib动态生成的代理类实例
     *  Method为上文中实体类所调用的被代理的方法引用
     *  Object[]为参数值列表
     *  MethodProxy为生成的代理类对方法的代理引用
     */
    @Override
    public Object intercept(java.lang.Object obj, Method m, java.lang.Object[] args, MethodProxy proxymethod) throws Throwable {

        System.out.println("cglib代理：----before----");
        // 代理类调用父类的方法
        // 调用代理类实例上的proxy方法的父类方法（即实体类UserServiceImpl中对应的方法）
        proxymethod.invokeSuper(obj, args);
        System.out.println("cglib代理：----after----");
        return null;
    }

}
