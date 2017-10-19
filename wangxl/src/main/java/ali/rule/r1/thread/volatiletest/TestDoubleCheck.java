package ali.rule.r1.thread.volatiletest;

/**
 * 优化后的单例模式
 * 双重枷锁 + volatile ，保证效率和单例
 * 如果不加volatie，只能保证有时候不出问题，但是当并发量特别大的时候，不能保证安全
 */
public class TestDoubleCheck {

	private static volatile TestDoubleCheck instance = null;

	private TestDoubleCheck() {
		System.out.println("构造函数被调用");
	}

	public static TestDoubleCheck getInstance() {
		if (instance == null) {
			synchronized (TestDoubleCheck.class) {
				// 加锁之后，如果注释掉这第二层null的判断，则会出问题
				if (instance == null) {
					instance = new TestDoubleCheck();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
        //创建实现了Runnable接口的匿名类
		Runnable run = new Runnable() {
			@Override
			public void run() {
				TestDoubleCheck.getInstance();
			}
		};
        for(int i = 0; i < 100; i++){
            Thread thread = new Thread(run);
            thread.setName("Thread"+i);  //设置名字
            thread.start();
        }
	}
}