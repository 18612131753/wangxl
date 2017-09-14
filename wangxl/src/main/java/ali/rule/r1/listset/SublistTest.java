package ali.rule.r1.listset;

import java.util.ArrayList;
import java.util.List;

public class SublistTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		List<Integer> lsits = list.subList(2, 5);
		System.out.println(lsits.get(0));
		//对sublist的操作，会影响到Arraylist的数据
		lsits.add( 10 );
		for(Integer i : list ){
			System.out.print(list.get(i)+" ");
		}

	}
}
