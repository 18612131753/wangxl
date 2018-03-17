package design.ray.a2_moshi.a1_single.a2_lanhan;


/**
 * 测试类，防止单例被多次调用
 * */
public class Test {

    public static void main(String[] args) {
        for( int i=0;i<100 ;i++){
            new Thread( ){
                public void run(){
                    Singleton.getInstance();
                }
            }.start();
        } 
    }

}
