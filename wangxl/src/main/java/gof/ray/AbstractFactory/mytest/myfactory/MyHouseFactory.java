package gof.ray.AbstractFactory.mytest.myfactory;

import gof.ray.AbstractFactory.mytest.factory.Diji;
import gof.ray.AbstractFactory.mytest.factory.Fangzi;
import gof.ray.AbstractFactory.mytest.factory.FangziFactory;
import gof.ray.AbstractFactory.mytest.factory.Louceng;

public class MyHouseFactory extends FangziFactory {

    @Override
    public Diji createDiji(String name, int num) {
        return new MyDiji(name, num);
    }

    @Override
    public Louceng createLouceng(String name, int num) {
        return new MyLouceng(name, num);
    }

    @Override
    public Fangzi createFangzi() {
        return new MyHouse();
    }

}
