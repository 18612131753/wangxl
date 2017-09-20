package ali.rule.r1.thread.threadpoolexecutor;

import java.util.concurrent.ExecutorService;

/**
 * 基本线程10个，最大线程池30个
 * 缓存阻塞队列10个
 * 每个线程执行休息5秒，要足够的延迟
 * 
 * 所以最大可以跑40个线程，30个在执行，10个在缓存，从第41个开始，将错误
 */
public class TestMain {
	// 测试构造的线程池
		public static void main(String[] args) {
			CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
			// 1.初始化
			exec.init();

			ExecutorService pool = exec.getCustomThreadPoolExecutor();
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