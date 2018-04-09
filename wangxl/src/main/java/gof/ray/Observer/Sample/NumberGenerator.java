package gof.ray.Observer.Sample;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 表示生成数值的对象的抽象类
 * */
public abstract class NumberGenerator {
    private ArrayList<Observer> observers = new ArrayList<Observer>(); // 保存Observer们

    // 注册Observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 删除Observer
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    // 向Observer发送通知
    public void notifyObservers() {
        Iterator<Observer> it = observers.iterator();
        while (it.hasNext()) {
            Observer o = it.next();
            o.update(this);
        }
    }

    public abstract int getNumber(); // 获取数值

    public abstract void execute(); // 生成数值
}
