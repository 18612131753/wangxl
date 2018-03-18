package gof.test.abstractfactory.myfactory;

import gof.test.abstractfactory.factory.Diji;

public class MyDiji extends Diji{

    public MyDiji(String name, int num) {
        super(name, num);
    }

    @Override
    public String makeItem() {
        return "打造地基："+num +" 米， "+ name +" 的材质";
    }

}
