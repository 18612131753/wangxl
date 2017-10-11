package com.ray.jvm;

public class StrInsern6and7 {
	/**
	 * 打印结果是 jdk6 下false false 
	 * jdk7,8 下false true 关键点是 jdk7
	 * 中常量池不在Perm区域了，这块做了调整。常量池中不需要再存储一份对象了，可以直接存储堆中的引用。这份引用指向s3引用的对象。
	 * 也就是说引用地址是相同的。
	 */
	public static void main(String[] args) {
		String s = new String("1");
		s.intern();
		String s2 = "1";
		System.out.println(s == s2);

		String s3 = new String("1") + new String("1");
		s3.intern();
		String s4 = "11";
		System.out.println(s3 == s4);

		//Double不存在于常量池中
		Double dd = new Double("1.0");
		Double dd1 = 1.0D;
		Double dd2 = 1.0D;
		System.out.println((dd == dd1) + "  " + (dd1 == dd2));

		//Byte,Short,Integer,Long,Character这5种整型的包装类也只是在对应值在-128到127时才可使用对象池。因为他们只占用一个字节
		Integer a = 127;
		Integer b = 127;
		Integer c = 128;
		Integer d = 128;
		System.out.println((a == b) +  " " +(c == d));
	}
}
