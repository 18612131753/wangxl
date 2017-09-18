package ali.rule.r1.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ComTest {

	public static void main(String[] args) {

		ConsumInfo consumInfo1 = new ConsumInfo(100, "consumInfo1", 400.0, new Date());
		ConsumInfo consumInfo2 = new ConsumInfo(200, "consumInfo2", 200.0, new Date());
		ConsumInfo consumInfo3 = new ConsumInfo(300, "consumInfo3", 100.0, new Date());
		ConsumInfo consumInfo4 = new ConsumInfo(400, "consumInfo4", 700.0, new Date());
		ConsumInfo consumInfo5 = new ConsumInfo(500, "consumInfo5", 800.0, new Date());
		ConsumInfo consumInfo6 = new ConsumInfo(600, "consumInfo6", 300.0, new Date());
		ConsumInfo consumInfo7 = new ConsumInfo(700, "consumInfo7", 900.0, new Date());
		ConsumInfo consumInfo8 = new ConsumInfo(800, "consumInfo8", 400.0, new Date());

		List<ConsumInfo> list = new ArrayList<ConsumInfo>();
		list.add(consumInfo1);
		list.add(consumInfo2);
		list.add(consumInfo3);
		list.add(consumInfo4);
		list.add(consumInfo5);
		list.add(consumInfo6);
		list.add(consumInfo7);
		list.add(consumInfo8);

		// 1. 使用Comparable排序
		//ComTest.comparableTest(list);
		
		// 2. 使用Comparator排序
		//ComTest.comparatorTest(list);
		
		// 3. 使用错误的Comparator排序
		ComTest.errorComparatorTest(list);
	}

	// 1. 使用Comparable排序
	public static void comparableTest(List<ConsumInfo> list) {
		System.out.println("排序前：");
		// 排序前
		for (ConsumInfo consumInfo : list) {
			System.out.println(consumInfo);
		}

		Collections.sort(list);// 排序
		System.out.println("排序后：");
		// 排序后
		for (ConsumInfo consumInfo : list) {
			System.out.println(consumInfo);
		}
	}

	// 2. 使用Comparator排序
	public static void comparatorTest(List<ConsumInfo> list) {
		System.out.println("排序前：");
		// 排序前
		for (ConsumInfo consumInfo : list) {
			System.out.println(consumInfo);
		}
		ComparatorConsunInfo comparatorConsunInfo = new ComparatorConsunInfo( "DESC" );// 比较器
		Collections.sort(list, comparatorConsunInfo);// 排序
		System.out.println("排序后：");
		// 排序后
		for (ConsumInfo consumInfo : list) {
			System.out.println(consumInfo);
		}
	}
	
	// 3. 使用错误的Comparator排序
	public static void errorComparatorTest(List<ConsumInfo> list) {
	
		ComparatorConsunInfoError comparatorConsunInfo = new ComparatorConsunInfoError(  );
		Collections.sort(list, comparatorConsunInfo);// 排序

		// 排序后
		for (ConsumInfo consumInfo : list) {
			System.out.println(consumInfo);
		}
	}
}
