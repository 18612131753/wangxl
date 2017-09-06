package ali.rule.r1;

public class EqualsTest {

	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		//Integer 的equals方法就是intValue的比较
		System.out.println( (a == b) +"  " + (a.equals(b)) +"  " +(a.intValue() == b.intValue()) );
	}
}