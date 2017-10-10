package ali.rule.r1.thread.countdown.threadjoin;

/**
 *
 *
 */
public class Worker extends Thread {

	//工作者名
	private String name;
	//工作时间
	private long time;
	
	public Worker(String name, long time) {
		this.name = name;
		this.time = time;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(name+"开始工作");
			Thread.sleep(time);
			System.out.println(name+"工作完成，耗费时间="+time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}

