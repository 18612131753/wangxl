package gof.ray.Strategy.Sample;

import java.util.Random;

/**
 *  策略1：如果这局胜，下面下一局出同样的手势。如果输了，随机手势
 * */
public class WinningStrategy implements Strategy {
    
    private Random random;
    private boolean won = false; // 上一局是否胜利，false=输
    private Hand prevHand;  //上一局手势

    public WinningStrategy() {
        random = new Random();
    }

    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));  //如果输了随机出手势
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}
