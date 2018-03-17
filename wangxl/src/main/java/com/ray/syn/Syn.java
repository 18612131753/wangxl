package com.ray.syn;

public class Syn {

    private String s1;
    private String s2;
    
    public Syn(String s1 ,String s2){
        this.s1 = s1 ;
        this.s2 = s2;
    }
    
    public  void sysS1(){
      // synchronized(Syn.class){
        synchronized( this ){
        for(int i=0;i<5;i++){
            System.out.println( Thread.currentThread().getName()+" "+s1+" "+i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      }
    }
}
