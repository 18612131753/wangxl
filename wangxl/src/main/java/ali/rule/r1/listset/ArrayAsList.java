package ali.rule.r1.listset;

import java.util.Arrays;
import java.util.List;

public class ArrayAsList {

	public static void main(String[] args) {
		// asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法
		String[] str = new String[] { "a", "b" ,"c"};
		List<String> list = Arrays.asList(str);
		// list.add("d");  //报异常
		str[0] = "a0";
		System.out.println( list.get(0)) ;
	}
}
