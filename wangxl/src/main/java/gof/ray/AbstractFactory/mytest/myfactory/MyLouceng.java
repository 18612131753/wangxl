package gof.ray.AbstractFactory.mytest.myfactory;

import gof.ray.AbstractFactory.mytest.factory.Louceng;

public class MyLouceng extends Louceng{

    public MyLouceng(String name, int num) {
        super(name, num);
    }

    @Override
    public String makeItem() {
        // TODO Auto-generated method stub
        return "打造楼层："+num +" 层，材质是 "+name;
    }

}
