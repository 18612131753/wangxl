package design.ray.a1_yuanze.aa3_dip.a1;

/**
 * 如果要实现吃蛋糕，则需要多谢方法，多态
 * */
public class People {

    public void eat(Pizza pizza){
        System.out.println( "吃了 "+pizza.getContent() );
    }
    
    //方法的重载，多态
    public void eat(Cake cake){
        System.out.println( "吃了 "+cake.getContent() );
    }
}
