package gof.ray.AbstractFactory.mytest.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象房子
 * */
public abstract class Fangzi {

    private List<Item> list = new ArrayList<Item>() ;
    
    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public void addItem(Item item ){
        list.add(item);
    }
    
    public abstract void makeFangzi() ;
}
