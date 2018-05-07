package gof.ray.Observer.Sample;

public class Main {
    public static void main(String[] args) {

        //观察对象
        NumberGenerator generator = new RandomNumberGenerator();
        //观察者
        Observer observer1 = new DigitObserver(); // 数字
        Observer observer2 = new GraphObserver(); // 图形
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.execute();
    }
}
