package ali.rule.r1.thread;

public class Singleton{

	private static Singleton singleton = null;

	private Singleton() {
		System.out.println("构造函数被调用");
	}

	public static Singleton getInstance() {
		if (singleton == null) {
			//线程不安全
//			singleton = new Singleton();
			//线程安全
			synchronized (Singleton.class) {
				singleton = new Singleton();
			}
		}
		return singleton;
	}

}