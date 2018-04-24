package gof.ray.Singleton.up;

/**
 * 一般的懒汉式
 */
public class Singleton1 {

    private static Singleton1 singleton;

    private Singleton1() {
        System.out.println("构造函数被调用");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 线程不安全
    public static Singleton1 getInstance() {
        if (singleton == null) {
            singleton = new Singleton1();
        }
        return singleton;
    }
}