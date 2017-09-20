package ali.rule.r1.thread.threadpoolexecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// 线程工厂类
public class CustomThreadFactory implements ThreadFactory {

	//AtomicInteger，一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。
	//而AtomicInteger则通过一种线程安全的加减操作接口。
	private AtomicInteger count = new AtomicInteger(0);

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		String threadName = "thread-new-"+ count.addAndGet(1); //自增1
		System.out.println("创建线程: "+threadName);
		t.setName(threadName);
		return t;
	}
}
