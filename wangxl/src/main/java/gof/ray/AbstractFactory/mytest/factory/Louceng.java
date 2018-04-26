package gof.ray.AbstractFactory.mytest.factory;

//楼层 - 零件
public abstract class Louceng extends Item {

    public int num; // 多少层楼

    public Louceng(String name, int num) {
        super(name);
        this.num = num;
    }

}
