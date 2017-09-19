package ali.rule.r1.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {

	public static void main(String[] args) {
		 // 创建一个可重用固定线程数的线程池  
        ExecutorService pool = Executors.newFixedThreadPool(5);
//        ExecutorService pool = Executors.newCachedThreadPool();
//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newScheduledThreadPool(1);
        Thread t1 = new MyThread();
        t1.setName("t1");
        Thread t2 = new MyThread();
        t2.setName("t2");
        Thread t3 = new MyThread();  
        t3.setName("t3");
        Thread t4 = new MyThread();
        t4.setName("t4");
        Thread t5 = new MyThread();
        t5.setName("t5");
        // 将线程放入池中进行执行  
        pool.execute(t1);  
        pool.execute(t2);  
        pool.execute(t3);  
        pool.execute(t4);  
        pool.execute(t5);
        // 关闭线程池  
        pool.shutdown();  
	}

}
class MyThread extends Thread {  
    @Override  
    public void run() {
        System.out.println( Thread.currentThread().getName() +"#" + this.getName() + "正在执行。。。");  
    }  
}  