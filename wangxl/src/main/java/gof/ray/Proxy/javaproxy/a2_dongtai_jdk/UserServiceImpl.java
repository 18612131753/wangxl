package gof.ray.Proxy.javaproxy.a2_dongtai_jdk;

/**
 * 创建业务接口实现类
 * 
 */
public class UserServiceImpl implements UserService {
    @Override
    public String add() {
        System.out.println("----------add----------");
        return "ok";
    }
}
