package gof.ray.Observer.javaobserver;

import java.util.Observable;
import java.util.Random;

/**
 * 生成随机数,被观察者
 */
public class RandomNumberGenerator extends Observable {
    
    private Random random = new Random(); // 随机数生成器

    public RandomNumberGenerator() {
        super();
    }

    public void execute() {
        for (int i = 0; i < 20; i++) {
            this.setChanged(); // 每次都要改变状态
            int number = random.nextInt(50);
            this.notifyObservers(number);
        }
    }

}
