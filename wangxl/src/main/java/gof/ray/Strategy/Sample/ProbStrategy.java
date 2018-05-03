package gof.ray.Strategy.Sample;

import java.util.Random;

/**
 *  策略2：根据上一局的手势，从概率上计算出下一局的收拾
 * */
public class ProbStrategy implements Strategy {
    
    private Random random;
    private int prevHandValue = 0;    //上一局手势
    private int currentHandValue = 0; //当前手势
    private int[][] history = { { 1, 1, 1, }, { 1, 1, 1, }, { 1, 1, 1, } } ;

    public ProbStrategy() {
        random = new Random();
    }

    @Override
    public Hand nextHand() {
        //出固定手势，一共胜利的次数
        int bet = random.nextInt(getSum(currentHandValue)); //在总次数中随机一个数，然后根据数的大小来决定出什么（概率）
        int handvalue = 0;
        if (bet < history[currentHandValue][0]) {
            handvalue = 0;
        } else if (bet < history[currentHandValue][0] + history[currentHandValue][1]) {
            handvalue = 1;
        } else {
            handvalue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handvalue;
        return Hand.getHand(handvalue);
    }

    private int getSum(int hv) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += history[hv][i];
        }
        return sum;
    }

    @Override
    public void study(boolean win) {
        if (win) {
            history[prevHandValue][currentHandValue]++;
        } else {
            //这么做，是为了在输之后，另外两个手势的概率更大
            history[prevHandValue][(currentHandValue + 1) % 3]++;
            history[prevHandValue][(currentHandValue + 2) % 3]++;
        }
    }
}
