package gof.ray.Observer.Sample;

/**
 * 定义观察者接口
 */
public interface Observer {
    public abstract void update(NumberGenerator generator);
}
