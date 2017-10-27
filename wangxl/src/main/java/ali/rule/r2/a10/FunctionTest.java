package ali.rule.r2.a10;

import java.util.function.Function;

public class FunctionTest {

	public static void main(String[] args) {
		//第一个Integer，入参类型   第二个，返回值类型
		Function<Integer, Integer> f = ( a ) -> { return a+1; };
		
		Integer t = f.apply( 3 );
		System.out.println( t );
	}

}
