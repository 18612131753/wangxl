package gof.ray.State.test;

public class StatusTwo implements Status {
    private final Value value = Value.TWO;

    /**
     * 获取状态2对象的标志性字段值，并通过回调更改Switcher对象的当前状态
     * 
     * @param switcher
     * @return
     */
    @Override
    public Value getStatusValue(Switcher switcher) {
        Value val = this.value;
        switcher.setCurrent(switcher.getOne());
        return val;
    }

    @Override
    public void action(Switcher switcher) {
    }
}
