package gof.ray.Singleton.up;

public class Test {

    public static void main(String[] args) {
        // 创建实现了Runnable接口的匿名类
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //Singleton1.getInstance();
                //Singleton2.getInstance();
                //Singleton3.getInstance();
                Singleton4.getInstance();
            }
        };
        for (int i = 0; i < 150; i++) {
            Thread thread = new Thread(run);
            thread.setName("Thread" + i); // 设置名字
            thread.start();
        }
    }

}
