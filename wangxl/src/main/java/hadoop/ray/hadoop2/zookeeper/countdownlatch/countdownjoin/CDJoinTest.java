package hadoop.ray.hadoop2.zookeeper.countdownlatch.countdownjoin;

import java.util.concurrent.CountDownLatch;


/**
 * 多线程同步工作
 * worker0 、worker1 两步干完了，再干worker2
 * */
public class CDJoinTest {

	public static void main(String[] args) throws InterruptedException {  

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker0 = new Worker("worker0", (long) (Math.random()*2000+3000), countDownLatch);  
        Worker worker1 = new Worker("worker1", (long) (Math.random()*2000+3000), countDownLatch);  
        Worker worker2 = new Worker("worker2", (long) (Math.random()*2000+3000), countDownLatch);  
          
        worker0.start();  
        worker1.start();  
          
        countDownLatch.await();  
        System.out.println("准备工作就绪");  
        worker2.start();          
    } 
	
}
