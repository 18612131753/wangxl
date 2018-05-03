package gof.ray.Strategy.Sample;

/**
 * 出拳的手势类
 */
public class Hand {

    public static final int HANDVALUE_GUU = 0; // 表示石头的值
    public static final int HANDVALUE_CHO = 1; // 表示剪刀的值
    public static final int HANDVALUE_PAA = 2; // 表示布的值
    public static final Hand[] hand = { // 表示猜拳中3种手势的实例
            new Hand(HANDVALUE_GUU), new Hand(HANDVALUE_CHO), new Hand(HANDVALUE_PAA), };

    // 表示猜拳中手势所对应的字符串
    private static final String[] name = { "石头", "剪刀", "布", };
    // 表示猜拳中出的手势的值
    private int handvalue;

    private Hand(int handvalue) {
        this.handvalue = handvalue;
    }

    // 根据手势的值获取其对应的实例
    public static Hand getHand(int handvalue) {
        return hand[handvalue];
    }

    // 如果this胜了h则返回true
    public boolean isStrongerThan(Hand h) {
        return fight(h) == 1;
    }

    // 如果this输给了h则返回true
    public boolean isWeakerThan(Hand h) {
        return fight(h) == -1;
    }

    // 判断胜负的算法,0>1,1>2,2>0
    private int fight(Hand h) { // 计分：平0, 胜1, 负-1
        if (this == h) {
            return 0;
        } else if ((this.handvalue + 1) % 3 == h.handvalue) { // 表示胜利
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() { // 转换为手势值所对应的字符串
        return name[handvalue];
    }
}
