package gof.test.abstractfactory.myfactory;

import gof.test.abstractfactory.factory.Diji;
import gof.test.abstractfactory.factory.Fangzi;
import gof.test.abstractfactory.factory.FangziFactory;
import gof.test.abstractfactory.factory.Louceng;

public class MyHourseFactory extends FangziFactory {

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

        return new MyHourse();
    }

}
