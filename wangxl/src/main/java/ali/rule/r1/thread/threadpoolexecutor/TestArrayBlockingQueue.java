package ali.rule.r1.thread.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;

public class TestArrayBlockingQueue {

	public static void main(String[] args) throws InterruptedException{
		//阻塞队列
		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
		for( int i=0;i<20;i++){
			//abq.add("s"+i);    //add会抛异常
			//abq.put("s"+i);    //put会阻塞在那里
			boolean  s = abq.offer("a"+i);  //offer会返回false,放的进去为true，放不进去为false
			System.out.println( i +"  " + s+"  " +abq.element() +"  "+abq.peek());
		}
	}
}
