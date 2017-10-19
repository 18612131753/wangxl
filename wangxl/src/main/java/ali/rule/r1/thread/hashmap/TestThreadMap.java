package ali.rule.r1.thread.hashmap;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 *  HashMap 在容量不够进行 resize 时由于高并发可能出现死链
 * 
 * */
public class TestThreadMap {

	public static HashMap<String,String> map = new HashMap<String,String>();
	
	public static int count = 5000 ;
	
	public static CountDownLatch cdl = new CountDownLatch(1);
	public static CountDownLatch cdl_t = new CountDownLatch(TestThreadMap.count);
	
	public static void main(String[] args) throws InterruptedException {
		 //创建实现了Runnable接口的匿名类
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					TestThreadMap.cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0;i<1000;i++){
					TestThreadMap.map.put( UUID.randomUUID().toString()+System.currentTimeMillis() , UUID.randomUUID().toString() );
				}
				cdl_t.countDown();
			}
		};
		for( int i=0;i< TestThreadMap.count ;i++){
			Thread t = new Thread( run );
			t.start();
		}
		TestThreadMap.cdl.countDown();
		TestThreadMap.cdl_t.await();
		System.out.println( map.size());
//		for( Entry<String, String> e :TestThreadMap.map.entrySet()){
//			System.out.println(e.getKey()+" == "+e.getValue());
//		}
	}

}
