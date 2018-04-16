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
     * intercept 拦截，拦截所有目标类方法的调用 obj 目标类的实例 m 目标方法的反射对象 args 方法的参数 proxy 代理类的实例
     */
    @Override
    public Object intercept(java.lang.Object obj, Method m, java.lang.Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println("cglib代理：----before----");
        // 代理类调用父类的方法
        proxy.invokeSuper(obj, args);
        System.out.println("cglib代理：----after----");
        return null;
    }

}
