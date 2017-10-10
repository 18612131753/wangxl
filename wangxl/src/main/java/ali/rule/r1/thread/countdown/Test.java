package ali.rule.r1.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 假设10个人同时起跑，然后看最后谁先到达，10个人都到达，进程结束
 * 
 * */
public class Test {

	// 开始的倒数锁
	public static CountDownLatch begin = new CountDownLatch(1);
	// 结束的倒数锁
	public static CountDownLatch end = new CountDownLatch(10);

	public static void main(String[] args) throws InterruptedException {
		for (int index = 0; index < 10; index++) {
			(new Thread() {
				public void run() {
					try {
						Test.begin.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println(Thread.currentThread().getName() + " arrived");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						// 每个选手到达终点时，end就减一
						Test.end.countDown();
					}
				}
			}).start();
		}
		System.out.println("Game Start");
		// begin减1，计算变为0，开始游戏-所有线程都在Test.begin.await();当countDown时，数值为0，立刻开始
		begin.countDown();
		// 等待end变为0，即所有选手到达终点
		end.await();
		System.out.println("Game Over");

	}
}
