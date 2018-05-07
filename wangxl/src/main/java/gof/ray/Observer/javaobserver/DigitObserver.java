package gof.ray.Observer.javaobserver;

import java.util.Observable;

/**
 * 数字观察者
 */
public class DigitObserver implements java.util.Observer {

    
    //Observable observable 被观察者
    //Object obj,就是传进来的对象
    @Override
    public void update(Observable observable, Object obj) {
        System.out.println("DigitObserver:" + (int)obj);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

}
