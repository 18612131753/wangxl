package com.ray.jvm;

public class StrTest {

	public static void main(String[] args) {
// 1------------------
		// a,b 在方法区的运行时常量池
		String a = "123";
		String b = "123";
		// c是new出来的，在堆中
		String c = new String("123");
		// d 在方法区的运行时常量池
		String d = "1" + "2" + "3";
		System.out.println("1-------------------------------");
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(a == d);

// 2------------------
		/**
		 * http://www.cnblogs.com/wxgblogs/p/5635099.html
		 * */
		String strcc = new String("strcc");
		//加入常量池
		//strcc  存着堆中的引用，堆中对象指向常量池“strcc”地址
		strcc.intern();
		//strcc1   存着常量池地址
		String strcc1 = "strcc";
		System.out.println("2-------------------------------");
		System.out.println( strcc1 == strcc);

// 3------------------
		/**
		 * 在第一种情况下，因为常量池中没有“str01”这个字符串，所以会在常量池中生成一个对堆中的“str01”的引用，
		 * 而在进行字面量赋值的时候，常量池中已经存在，所以直接返回该引用即可，因此str1和str2都指向堆中的字符串，
		 * 返回true。
		 * */
		String str2 = new String("str") + new String("01"); // new+new是在堆中
		//加入常量池
		//jdk1.6，返回false
		//jdk1.7，返回true
		//intern时将“str01”存入常量池，因为1.7中常量池属于堆，所以常量池存储的是堆中的引用
		str2.intern();
		String str1 = "str01"; //存入常量池地址，常量池又是堆的引用，所以true
		System.out.println("3-------------------------------");
		System.out.println(str2 == str1);

// 4------------------
		/**
		 * 调换位置以后，因为在进行字面量赋值（String str8 = "str08"）的时候，
		 * 常量池中不存在，所以str8指向的常量池中的位置，而str81指向的是堆中的对象，
		 * 再进行intern方法时，对str1和str2已经没有影响了，
		 * 所以返回false。
		 * */
		String str8 = "str08";
		String str81 = new String("str") + new String("08");
		str81.intern();
		String str82 = "str08";
		System.out.println("4-------------------------------");
		System.out.println(str8 == str81);
		System.out.println(str82 == str81);
		System.out.println(str82 == str8);
		
	}
}
