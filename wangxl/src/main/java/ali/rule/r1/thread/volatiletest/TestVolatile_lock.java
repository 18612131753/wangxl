package ali.rule.r1.thread.volatiletest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile关键字能保证可见性没有错，但是上面的程序错在没能保证原子性。
 * 可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。
 * 
 * synchronized 保证结果正确
 */
public class TestVolatile_lock {

	public static int num = 0;
	public static Lock lock = new ReentrantLock();

	public static void increase() {
		TestVolatile_lock.lock.lock();
		TestVolatile_lock.num = TestVolatile_lock.num + 1;
		TestVolatile_lock.lock.unlock();
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
						TestVolatile_lock.increase();
					}
				}
			}.start();
		}
		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield();
		System.out.println(TestVolatile_lock.num);
	}
}
