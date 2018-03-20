package gof.ray.Strategy.A1;

public interface Strategy {
    public abstract Hand nextHand();
    public abstract void study(boolean win);
}
