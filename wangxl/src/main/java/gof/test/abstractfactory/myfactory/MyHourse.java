package gof.test.abstractfactory.myfactory;

import gof.test.abstractfactory.factory.Fangzi;
import gof.test.abstractfactory.factory.Item;

public class MyHourse extends Fangzi {

    @Override
    public void makeFangzi() {
        System.out.println( "开始" );
        for(Item item : this.getList() ){
            System.out.println( item.makeItem() );
        }
        System.out.println( "结束" );
    }

}
