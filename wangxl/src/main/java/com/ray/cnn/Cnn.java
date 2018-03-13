package com.ray.cnn;

public class Cnn {

    public static void main(String[] args) {
        int s=0;
        String ss = "";
        for(int a1=0;a1<10;a1++){
            for(int a2=a1+1;a2<10;a2++){
                for(int a3=a2+1;a3<10;a3++){
                    for(int a4=a3+1;a4<10;a4++){
                        for(int a5=a4+1;a5<10;a5++){
                            s++;
                            if( s < 10 ) {
                                ss = "A00"+s;
                            } else if( s< 100){
                                ss = "A0"+s;
                            } else {
                                ss = "A"+String.valueOf(s);
                            }
                            System.out.println( "insert into ttff_group values('"+ss+"','"+a1+""+a2+""+a3+""+a4+""+a5+"');");
                            
                        }
                    }
                }
            }
        }
        System.out.println(s);
    }

}
