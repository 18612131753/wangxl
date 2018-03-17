package design.ray.a2_moshi.a1_single.a1_ehan;

/**
 * 饿汉模式
 */
public class Singleton {

    private static Singleton singleton = new Singleton();

    private Singleton() {
        System.out.println("构造函数被调用");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        return singleton;
    }

}