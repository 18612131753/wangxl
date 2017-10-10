package ali.rule.r1.thread.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main( String[] args ){
		Timer timer = new Timer();
		//20s后开始执行，每秒执行一次
		timer.schedule(new TimerTask() {
	        public void run() {
	            System.out.println("111");
	        }
		}, 0 , 1000);
		
		timer.schedule(new TimerTask() {
	        public void run() {
	            System.out.println("222");
	        }
		}, 1000 , 1000);
		
		timer.schedule(new TimerTask() {
	        public void run() {
	            System.out.println("333");
	            String a = null ;
	            System.out.println( a.equals("") );  //会抛出异常
	        }
		}, 5000 , 1000);
	}
}
