package ali.rule.r1.thread.countdown.threadjoin;

/**
*  worker0 、worker1 两步干完了，再干worker2
**/
public class JoinTest {

	/**
	 * 当在当前线程中调用某个线程 thread 的 join() 方法时，
	 * 当前线程就会阻塞，直到thread 执行完成，当前线程才可以继续往下执行。
	 * 补充下：join的工作原理是，不停检查thread是否存活，如果存活则让当前线程永远wait
	 * ，直到thread线程终止，线程的this.notifyAll 就会被调用。
	 * */
	public static void main(String[] args) throws InterruptedException {

		Worker worker0 = new Worker("worker0", (long) (Math.random()*2000+3000));
		Worker worker1 = new Worker("worker1", (long) (Math.random()*2000+3000));
		Worker worker2 = new Worker("worker2", (long) (Math.random()*2000+3000));
		
		worker0.start();
		worker1.start();
		
		worker0.join();
		worker1.join();
		System.out.println("准备工作就绪");
		
		worker2.start();		
	}
}
