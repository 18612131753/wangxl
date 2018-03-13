package com.ray.suanfa;

/**
 * 计算字符串对称
 * 如：acbabccbadadz  aa
 * 结果：cc    bccb     abccba    aa
 * */
public class Duichen2 {

    public static void main(String[] args ){
        String s = "acbabccbadadz" ;
        System.out.println( s.substring(1, 2));
        System.out.println( s.substring(2, 3));
    }
}
