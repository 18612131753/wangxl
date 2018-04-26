package gof.ray.AbstractFactory.mytest;

import gof.ray.AbstractFactory.mytest.factory.Diji;
import gof.ray.AbstractFactory.mytest.factory.Fangzi;
import gof.ray.AbstractFactory.mytest.factory.FangziFactory;
import gof.ray.AbstractFactory.mytest.factory.Louceng;

public class Clinet {

    public static void main(String[] args) {
        FangziFactory factory = 
                FangziFactory.getFangziFactory("gof.ray.AbstractFactory.mytest.myfactory.MyHouseFactory");
        Diji diji1 = factory.createDiji("一般土地", 2);
        Diji diji2 = factory.createDiji("石头地", 2);
        
        Louceng louceng1 = factory.createLouceng("石灰墙", 2); //创建2层的石灰墙
        Louceng louceng2 = factory.createLouceng("砖头墙", 1);
        
        Fangzi fangzi = factory.createFangzi();
        fangzi.addItem( diji1 );
        fangzi.addItem( diji2 );
        fangzi.addItem( louceng1 );
        fangzi.addItem( louceng2 );
        
        fangzi.makeFangzi();
    }

}
