package gof.ray.Observer.javaobserver;

import java.util.Observable;

/**
 * 图形观察者
 */
public class GraphObserver implements java.util.Observer {
    @Override
    public void update(Observable observable, Object obj) {
        System.out.print("GraphObserver:");
        int count = (int) obj;
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println("");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
