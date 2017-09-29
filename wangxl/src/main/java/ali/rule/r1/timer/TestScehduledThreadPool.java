package ali.rule.r1.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScehduledThreadPool {
	public static void main(String[] args) {
		// 两种方式创建（建议第二种）：
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		/**
		 * 每隔2秒执行一次 new TestScehduledThread() 要执行的任务线程 1000：延迟多长时间执行 2000:
		 * 每隔多少长时间执行一次 TimeUnit.MILLISECONDS：时间单位
		 */
		Thread t1 = new TestScehduledThread();
		t1.setName("t1");
		long time = System.currentTimeMillis();
		System.out.println(time);
		pool.scheduleAtFixedRate(t1, 1000, 2000, TimeUnit.MILLISECONDS);
		// pool.shutdown(); //线程池关闭就不执行了

		Thread t3 = new TestScehduledThreadExecption();
		t3.setName("t3");
		pool.scheduleAtFixedRate(t3, 1000, 2000, TimeUnit.MILLISECONDS);
	}
}

class TestScehduledThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "#" + this.getName() + "正在执行。。。");

	}
}

class TestScehduledThreadExecption extends Thread {
	@Override
	public void run() {
		String a = null;
		try {
			a.equals("");
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "#" + this.getName() + "正在执行。。。");
	}
}