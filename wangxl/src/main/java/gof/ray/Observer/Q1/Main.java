package gof.ray.Observer.Q1;

import gof.ray.Observer.A1.DigitObserver;
import gof.ray.Observer.A1.GraphObserver;
import gof.ray.Observer.A1.IncrementalNumberGenerator;
import gof.ray.Observer.A1.NumberGenerator;
import gof.ray.Observer.A1.Observer;

public class Main {
    public static void main(String[] args) {
        NumberGenerator generator = new IncrementalNumberGenerator(10, 50, 5);  
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.execute();
    }
}
