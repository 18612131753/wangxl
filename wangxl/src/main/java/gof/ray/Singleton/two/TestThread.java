package gof.ray.Singleton.two;

public class TestThread {

	public static void main(String[] args) {
        //创建实现了Runnable接口的匿名类
		Runnable run = new Runnable() {
			@Override
			public void run() {
				Singleton.getInstance();
				// System.out.println(Thread.currentThread().getName());
			}
		};
        for(int i = 0; i < 150; i++){
            Thread thread = new Thread(run);
            thread.setName("Thread"+i);  //设置名字
            thread.start();
        }
	}
}
