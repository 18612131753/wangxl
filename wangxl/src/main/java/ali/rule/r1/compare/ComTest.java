package ali.rule.r1.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ComTest {

	public static void main(String[] args) {

		ConsumInfo consumInfo1 = new ConsumInfo(100, "consumInfo1", 400.0, new Date());
		ConsumInfo consumInfo2 = new ConsumInfo(200, "consumInfo1", 200.0, new Date());
		ConsumInfo consumInfo3 = new ConsumInfo(300, "consumInfo1", 100.0, new Date());
		ConsumInfo consumInfo4 = new ConsumInfo(400, "consumInfo1", 700.0, new Date());
		ConsumInfo consumInfo5 = new ConsumInfo(500, "consumInfo1", 800.0, new Date());
		ConsumInfo consumInfo6 = new ConsumInfo(600, "consumInfo1", 300.0, new Date());
		ConsumInfo consumInfo7 = new ConsumInfo(700, "consumInfo1", 900.0, new Date());
		ConsumInfo consumInfo8 = new ConsumInfo(800, "consumInfo1", 400.0, new Date());

		List<ConsumInfo> list = new ArrayList<ConsumInfo>();
		list.add(consumInfo1);
		list.add(consumInfo2);
		list.add(consumInfo3);
		list.add(consumInfo4);
		list.add(consumInfo5);
		list.add(consumInfo6);
		list.add(consumInfo7);
		list.add(consumInfo8);

		//ComTest.comparableTest(list);
		ComTest.comparatorTest(list);
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
}
