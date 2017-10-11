package ali.rule.r1.thread.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;

public class TestThreadlocalRandom {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			TaskLocalRandom tlk = new TaskLocalRandom();
			(new Thread(tlk)).start();
		}
	}

}

class TaskLocalRandom implements Runnable {
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s : %s\n", name, ThreadLocalRandom.current().nextInt(100));
		}
	}
}