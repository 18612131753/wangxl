package ali.rule.r1.listset;

import java.util.ArrayList;
import java.util.List;

public class ListToArrayTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add(i+"");
		}
		Object[] objarr = (String[])list.toArray(); //报错
		for(int i=0;i<10;i++){
			System.out.println( (String)objarr[i]);
		}
		
	}
}
