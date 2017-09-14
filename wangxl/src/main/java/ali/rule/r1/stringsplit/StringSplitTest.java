package ali.rule.r1.stringsplit;

public class StringSplitTest {

	public static void main(String[] args) {
		String str = "a,b,,c,,";
		Integer a = new Integer(1);
		String[] ary = str.split(",");
		// 预期大于 4，结果是 4
		System.out.println(ary.length);
		System.out.println(ary[0] + " " + ary[1] + " " + ary[2] + " " + ary[3] + " ");

		StringBuilder sb = new StringBuilder(); // 线程不安全
		StringBuffer sbf = new StringBuffer(); // 线程安全
	}

}
