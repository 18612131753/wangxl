package ali.rule.r1.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//定时执行线程池
public class TestScehduledThreadPool {
	public static void main(String[] args) {
		// 两种方式创建（建议第二种）：
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		// ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1); 
		 /** 
		  * 每隔2秒执行一次
         * new TestScehduledThread() 要执行的任务线程 
         * 1000：延迟多长时间执行 
         * 2000: 每隔多少长时间执行一次 
         * TimeUnit.MILLISECONDS：时间单位 
         */
		Thread t1 = new TestScehduledThread();
        t1.setName("t1");
        long time = System.currentTimeMillis();
    	System.out.println( time ) ;
		pool.scheduleAtFixedRate(t1, 1000, 2000, TimeUnit.MILLISECONDS); 
//		pool.shutdown();  //线程池关闭就不执行了
		
//		Thread t2 = new TestScehduledThread();
//      t2.setName("t2");
//      pool.scheduleAtFixedRate(t2, 1000, 2000, TimeUnit.MILLISECONDS); 
	}
}
class TestScehduledThread extends Thread {  
    @Override  
    public void run() {
        
    	long time = System.currentTimeMillis();
    	System.out.println( time ) ;
        System.out.println( Thread.currentThread().getName() +"#" + this.getName() + "正在执行。。。");
        
    }  
}