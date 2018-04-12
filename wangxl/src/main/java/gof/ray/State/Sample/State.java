package gof.ray.State.Sample;

public interface State {
    // 设置时间，切换内部State实例
    public abstract void doClock(Context context, int hour);

    // 使用金库
    public abstract void doUse(Context context);

    // 按下警铃
    public abstract void doAlarm(Context context);

    // 正常通话
    public abstract void doPhone(Context context);
}
