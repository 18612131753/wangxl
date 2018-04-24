package gof.ray.Singleton.up;

/**
 * 优化懒汉式
 */
public class Singleton4 {

    private static volatile Singleton4 singleton;

    private Singleton4() {
        System.out.println("构造函数被调用");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 线程安全,优化锁
    public static Singleton4 getInstance() {
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }   
            }
        }
        return singleton;
    }
}