package ali.rule.r1.thread.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基本线程10个，最大线程池30个
 * 缓存阻塞队列10个
 * 每个线程执行休息5秒，要足够的延迟
 * 所以第11-20个任务，会放到缓存的ArrayBlockingQueue队列中去，最后才执行
 * 
 * 所以最大可以跑40个线程，30个在执行，10个在缓存，从第41个开始，将错误
 */
public class TestMain {

		public static void main(String[] args) {
			/** 
		     * 线程池初始化方法 
		     *  
		     * corePoolSize 核心线程池大小----10 
		     * maximumPoolSize 最大线程池大小----30 
		     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit 
		     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES 
		     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列 
		     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂 
		     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时, 
		     *                          即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)), 
		     *                                任务会交给RejectedExecutionHandler来处理 
		     */
			ExecutorService pool = new ThreadPoolExecutor(
					10, 30, 30, TimeUnit.MINUTES, 
					new ArrayBlockingQueue<Runnable>(10),
					new CustomThreadFactory(), 
					new CustomRejectedExecutionHandler());
			for (int i = 1; i < 50; i++) {
				System.out.println("提交第" + i + "个任务!");
				MyThread t = new MyThread();
				t.setName("thread-"+i);
				pool.execute(t);
			}

			// 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
			// exec.destory();

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}
class MyThread extends Thread {  
    @Override  
    public void run() {
        System.out.println( Thread.currentThread().getName() +"#" + this.getName() + "正在执行。。。"); 
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }  
} 