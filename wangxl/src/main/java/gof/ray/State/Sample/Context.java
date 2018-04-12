package gof.ray.State.Sample;

/**
 * 管理金库状态，并与警报中心联系的接口
 * */
public interface Context {

    // 设置时间
    public abstract void setClock(int hour);

    // 改变状态
    public abstract void changeState(State state);

    // 联系警报中心
    public abstract void callSecurityCenter(String msg);

    // 在警报中心留下记录(使用金库)
    public abstract void recordLog(String msg);
}
