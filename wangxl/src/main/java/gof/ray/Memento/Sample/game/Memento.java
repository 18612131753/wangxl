package gof.ray.Memento.Sample.game;

import java.util.*;

public class Memento {
    int money; // 所持金钱
    ArrayList<String> fruits; // 当前获得的水果

    // 构造函数(wide interface)
    Memento(int money) {
        this.money = money;
        this.fruits = new ArrayList<String>();
    }

    // 获取当前所持金钱（narrow interface）
    public int getMoney() {
        return money;
    }

    // 添加水果(wide interface)
    void addFruit(String fruit) {
        fruits.add(fruit);
    }

    @SuppressWarnings("unchecked")
    // 获取当前所持所有水果（wide interface）
    List<String> getFruits() { // 获取当前所持所有水果（wide interface）
        return (List<String>) fruits.clone();
    }

    public String toString() { // 用字符串表示主人公状态
        return "[money = " + money + ", fruits = " + fruits + "]";
    }
}
