package gof.ray.Memento.A4.game;

import java.io.*;
import java.util.*;

public class Memento implements Serializable {
    private static final long serialVersionUID = 1L;
    int money; // 所持金钱
    ArrayList<String> fruits; // 获得的水果

    public int getMoney() { // 获取现在所持金钱(narrow interface)
        return money;
    }

    Memento(int money) { // 构造函数(wide interface)
        this.money = money;
        this.fruits = new ArrayList<String>();
    }

    void addFruit(String fruit) { // 赢得水果(wide interface)
        fruits.add(fruit);
    }

    @SuppressWarnings("unchecked")
    List<String> getFruits() { // 获取水果(wide interface)
        return (List<String>) fruits.clone();
    }
}
