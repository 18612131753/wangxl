package design.ray.a1_yuanze.aa2_lsp.a2;

public class Sheep extends Animal{

    @Override
    //重写的父类的方法，造成出错
    public void eat( String animal ){
        System.out.println( animal +"吃肉");
    }
    
    public void drink( String animal ){
        System.out.println( animal +"喝水");
    }
}
