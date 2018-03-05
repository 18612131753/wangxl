package ali.rule.r1.thread.volatiletest;

/**
 * volatile关键字能保证可见性没有错，但是上面的程序错在没能保证原子性。
 * 可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。
 * */
public class TestVolatile {

	public static volatile int num = 0;

	public static void increase(){
		TestVolatile.num = TestVolatile.num + 1;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						TestVolatile.increase();
					}
				}
			}.start();
		}
		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield(); //线程让步
		System.out.println(TestVolatile.num);
	}
}
