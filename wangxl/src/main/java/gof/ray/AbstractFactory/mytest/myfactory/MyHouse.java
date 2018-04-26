package gof.ray.AbstractFactory.mytest.myfactory;

import gof.ray.AbstractFactory.mytest.factory.Fangzi;
import gof.ray.AbstractFactory.mytest.factory.Item;

public class MyHouse extends Fangzi {

    @Override
    public void makeFangzi() {
        System.out.println( "开始" );
        for(Item item : this.getList() ){
            System.out.println( item.makeItem() );
        }
        System.out.println( "结束" );
    }

}
