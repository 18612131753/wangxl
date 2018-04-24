package gof.ray.Singleton.up;

/**
 * 一般的懒汉式
 */
public class Singleton3 {

    private static Singleton3 singleton;

    private Singleton3() {
        System.out.println("构造函数被调用");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 线程安全,优化锁
    public static Singleton3 getInstance() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}