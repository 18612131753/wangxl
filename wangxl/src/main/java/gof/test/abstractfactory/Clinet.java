package gof.test.abstractfactory;

import gof.test.abstractfactory.factory.Diji;
import gof.test.abstractfactory.factory.Fangzi;
import gof.test.abstractfactory.factory.FangziFactory;
import gof.test.abstractfactory.factory.Louceng;

public class Clinet {

    public static void main(String[] args) {
        FangziFactory factory = FangziFactory.getFangziFactory("gof.test.abstractfactory.myfactory.MyHourseFactory");
        Diji diji1 = factory.createDiji("一般土地", 2);
        Diji diji2 = factory.createDiji("石头地", 2);
        
        Louceng louceng1 = factory.createLouceng("石灰墙", 2);
        Louceng louceng2 = factory.createLouceng("砖头墙", 1);
        
        Fangzi fangzi = factory.createFangzi();
        fangzi.addItem( diji1 );
        fangzi.addItem( diji2 );
        fangzi.addItem( louceng1 );
        fangzi.addItem( louceng2 );
        
        fangzi.makeFangzi();
    }

}
