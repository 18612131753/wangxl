package com.ray.syn;

public class Test {

    public static void main(String[] args) {
        Syn syn = new Syn("s1","s2");
        Syn syn1 = new Syn("a1","a2");
        for(int i=0;i<2 ;i++){
            new Thread(){
                public void run(){
                    syn.sysS1();
                }
            }.start();
            new Thread(){
                public void run(){
                    syn1.sysS1();
                }
            }.start();
        }
    }

}
