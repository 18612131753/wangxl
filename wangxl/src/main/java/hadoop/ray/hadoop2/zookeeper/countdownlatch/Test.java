package hadoop.ray.hadoop2.zookeeper.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Test {

	public static CountDownLatch cdl = new CountDownLatch(5);
	
	public static void main(String[] args) throws InterruptedException{
		
		(new Thread() {
            public void run() {
        			try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   System.out.println("Thread run");
            }
         }).start();
		
		System.out.println("end");
		
	}
}
