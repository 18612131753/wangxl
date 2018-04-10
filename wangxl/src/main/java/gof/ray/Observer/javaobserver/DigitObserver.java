package gof.ray.Observer.javaobserver;

import java.util.Observable;

/**
 * 数字观察者
 */
public class DigitObserver implements java.util.Observer {

    @Override
    public void update(Observable observable, Object obj) {
        System.out.println("DigitObserver:" + (int)obj);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

}
