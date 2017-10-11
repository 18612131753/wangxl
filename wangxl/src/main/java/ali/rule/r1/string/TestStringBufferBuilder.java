package ali.rule.r1.string;

import java.util.concurrent.CountDownLatch;
/**
 * 此示例程序做了如下事情：
	基于初始字符串“AAAABBBB”分别构建StringBuffer和StringBuilder对象
	分别启动1000个线程，调用StringBuffer和StringBuilder的reverse方法，进行字符串反转
	所有线程执行完后打印结果，由于反转偶数次，线程安全的对象输出应与初始值相同，线程不安全的对象则可能产生乱序
 * */
public class TestStringBufferBuilder {

	private static final int THREAD_NUM = 100;

	public static StringBuffer  stringBuffer  = new StringBuffer ( "AAAABBBB" );
	public static StringBuilder stringBuilder = new StringBuilder( "AAAABBBB" );
	
	public static CountDownLatch countDownLatch1 = new CountDownLatch( THREAD_NUM );
	public static CountDownLatch countDownLatch2 = new CountDownLatch( THREAD_NUM );
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		try {
			for (int i = 0; i < THREAD_NUM; i++) {
				new StringBufferTaskThread().start();
			}
			countDownLatch1.await();
			System.out.println("StringBuffer toString: " + stringBuffer.toString());
			
			for (int i = 0; i < THREAD_NUM; i++) {
				new StringBuilderTaskThread().start();
			}
			countDownLatch2.await();
			System.out.println("StringBuilder toString: " + stringBuilder.toString());
			long endTime = System.currentTimeMillis();
			System.out.println("Running time: " + (endTime - startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class StringBufferTaskThread extends Thread {
	@Override
	public void run() {
		try {
			Thread.sleep(600);
			TestStringBufferBuilder.stringBuffer.reverse();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestStringBufferBuilder.countDownLatch1.countDown();
	}
}
class StringBuilderTaskThread extends Thread {
	@Override
	public void run() {
		try {
			Thread.sleep(600);
			TestStringBufferBuilder.stringBuilder.reverse();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestStringBufferBuilder.countDownLatch2.countDown();
	}
}