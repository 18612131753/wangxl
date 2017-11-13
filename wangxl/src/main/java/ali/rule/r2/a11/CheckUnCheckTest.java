package ali.rule.r2.a11;

public class CheckUnCheckTest {

	// 方法必须抛出check异常
	public void first() throws MyCheckException {
		throw new MyCheckException("checked exception");
	}

	// uncheck异常不需要throw
	public void second(String msg) {
		if (msg == null) {
			throw new NullPointerException("unchecked exception");
		}
	}

	public void third() throws MyCheckException {
		first();
	}

	public static void main(String[] args) {
		CheckUnCheckTest ve = new CheckUnCheckTest();

		try {
			ve.first();
		} catch (MyCheckException e) {
			e.printStackTrace();
		}
		
		ve.second(null);
	}

}
