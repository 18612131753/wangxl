package gof.ray.AbstractFactory.mytest.factory;

//零件父类
public abstract class Item {

    public String name ;
    
    public Item(String name ){
        this.name = name ;
    }
    
    //建造零件
    public abstract String makeItem();
    
}
