package gof.ray.Decorator.yizhixing.jicheng;

public class Child extends Father{

    public void childMethod(){
        
    }
    
    public static void main(String[] args){
        Father child = new Child();
        child.parentMethod();     //可以像操作父类一样操作方法
        ((Child)child).childMethod(); //类型转换后，操作子类的方法
    }
}
