package ali.rule.r1.listset;

import java.util.ArrayList;
import java.util.List;

public class ArrayForRemave {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		for (String temp : a) {
			System.out.println("# " +temp);
			if ("1".equals(temp)) {  //不会报错
			//if ("2".equals(temp)) {  //会报错，因为删除最后一个元素后，循环会出问题，list最大长度改变
				a.remove(temp);
			}
		}
	}

}
