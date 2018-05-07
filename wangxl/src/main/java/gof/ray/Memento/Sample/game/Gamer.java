package gof.ray.Memento.Sample.game;

import java.util.*;

public class Gamer {

    private int money; // 所持金钱
    private List<String> fruits = new ArrayList<String>(); // 获得的水果
    private Random random = new Random(); // 随机数生成器

    private static String[] fruitsname = { // 表示水果种类的数组
            "苹果", "葡萄", "香蕉", "橘子", };

    public Gamer(int money) { // 构造函数
        this.money = money;
    }

    public int getMoney() { // 获取当前所持金钱
        return money;
    }

    // 投掷骰子进行游戏
    public void bet() {
        int dice = random.nextInt(6) + 1; // 掷骰子
        System.out.println("掷骰子：" + dice);
        if (dice == 1) { // 骰子结果为1…增加所持金钱
            money += 100;
            System.out.println("所持金钱增加了。");
        } else if (dice == 2) { // 骰子结果为2…所持金钱减半
            money /= 2;
            System.out.println("所持金钱减半了。");
        } else if (dice == 3 || dice == 4 || dice == 5) { // 骰子结果为345…获得水果
            String f = this.getFruit();
            System.out.println("获得了水果(" + f + ")。");
            fruits.add(f);
        } else { // 骰子结果为6则什么都不会发生
            System.out.println("什么都没有发生。");
        }
    }

    // 随机获得一个水果
    private String getFruit() {
        return fruitsname[random.nextInt(fruitsname.length)];
    }

    // 拍摄快照
    public Memento createMemento() {
        Memento m = new Memento(money);
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            String f = (String) it.next();
            m.addFruit(f);
        }
        return m;
    }

    // 撤销
    public void restoreMemento(Memento memento) {
        this.money = memento.getMoney();
        this.fruits = memento.getFruits();
    }

    public String toString() { // 用字符串表示主人公状态
        return "[money = " + money + ", fruits = " + fruits + "]";
    }

}
