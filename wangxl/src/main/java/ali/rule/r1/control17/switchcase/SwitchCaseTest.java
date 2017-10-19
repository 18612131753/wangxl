package ali.rule.r1.control17.switchcase;

public class SwitchCaseTest {

	public static void main(String[] args) {
		int s = 1;
		s = SwitchCaseTest.swich(s);
		System.out.println(s);
	}
	
	public static int swich(int s){
		switch (s) {
		case 0:
			s++;
			break;
		case 1:
			s++;
			return s ;
		default:
			break;
		}
		return s ;
	}

}
