package gof.ray.Strategy.Sample;

/**
 *  策略接口
 * */
public interface Strategy {

    //下一局出拳的手势
    public abstract Hand nextHand();
    
    //根据结果学习，win = true表示获胜，false 表示输了
    public abstract void study(boolean win);

}
