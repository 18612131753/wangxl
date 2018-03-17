package ali.rule.r1.thread;

public class Singleton{

	private static Singleton singleton = null;

	private Singleton() {
		System.out.println("构造函数被调用");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//线程不安全
	public static Singleton getInstance() {
	//线程安全
	//public static synchronized Singleton getInstance() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}