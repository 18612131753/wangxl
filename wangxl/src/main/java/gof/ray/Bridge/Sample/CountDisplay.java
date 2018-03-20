package gof.ray.Bridge.Sample;

//类的功能层次结构
public class CountDisplay extends Display {
    
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    
    //增加了新功能
    public void multiDisplay(int times) {       // 循环显示times次
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
