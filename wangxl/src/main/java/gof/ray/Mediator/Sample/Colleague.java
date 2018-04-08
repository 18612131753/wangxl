package gof.ray.Mediator.Sample;

/**
 * 定义组件接口
 */
public interface Colleague {
    //设置中介者
    public abstract void setMediator(Mediator mediator);
    
    //告知仲裁者，true 表示启用，false表示禁用
    public abstract void setColleagueEnabled(boolean enabled);
}
