package com.ray.suanfa;

/**
 * 计算字符串对称
 * 如：acbabccbadadz  aa
 * 结果：cc    bccb     abccba    aa
 * */
public class Duichen {

    public static void main(String[] args ){
        String s = "acbabccbadadzaa" ;
        int ss=0;
        for( int i=0;i<s.length();i++){
            for( int j=i;j<s.length();j++){
                ss++;
                if( (j+1+j+1-i)>s.length() ) break;
                
                String start = s.substring(i, j+1);
                String end = s.substring(j+1, (j+1+j+1-i)>s.length()?s.length():(j+1+j+1-i));
                StringBuffer end_sb = new StringBuffer (end);
                // System.out.println(start +" ! "+end_sb.reverse());
                if(start.equals( end_sb.reverse().toString()) ){
                    System.out.println( i+" to "+(j+1)+"  "+start +" = "+end);
                }
            }
        }
        System.out.println( ss );
    }
}
