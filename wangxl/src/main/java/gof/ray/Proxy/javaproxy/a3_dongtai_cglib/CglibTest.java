package gof.ray.Proxy.javaproxy.a3_dongtai_cglib;

public class CglibTest {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        UserServiceImpl us = (UserServiceImpl) proxy.getProxy(UserServiceImpl.class);
        us.add();
    }

}
