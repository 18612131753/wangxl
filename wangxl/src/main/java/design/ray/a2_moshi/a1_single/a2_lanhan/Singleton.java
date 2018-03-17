package design.ray.a2_moshi.a1_single.a2_lanhan;

/**
 * 懒汉模式
 */
public class Singleton {

    private static volatile Singleton singleton = null;

    private Singleton() {
        System.out.println("构造函数被调用:" + Thread.currentThread().getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}