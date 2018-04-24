package gof.ray.Singleton.up;

/**
 * 一般的懒汉式
 */
public class Singleton2 {

    private static Singleton2 singleton;

    private Singleton2() {
        System.out.println("构造函数被调用");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 线程安全
    public synchronized static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}