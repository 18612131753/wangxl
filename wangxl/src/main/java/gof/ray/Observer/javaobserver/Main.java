package gof.ray.Observer.javaobserver;

import java.util.Observer;

public class Main {

    public static void main(String[] args) {

        RandomNumberGenerator generator = new RandomNumberGenerator();
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        generator.addObserver(observer1);
        generator.addObserver(observer2);

        generator.execute();
    }
}
