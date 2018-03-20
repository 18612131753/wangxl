package gof.ray.Bridge.Sample;

public class Display {
    
    private DisplayImpl impl; // 适配器模式- 对象委托模式

    public Display(DisplayImpl impl) { //类的实现层次结构
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}
