package gof.test.abstractfactory.factory;

//零件 - 地基
public abstract class Diji extends Item {

    public int num; // 地基多少米

    public Diji(String name, int num) {
        super(name);
        this.num = num;
    }

}
