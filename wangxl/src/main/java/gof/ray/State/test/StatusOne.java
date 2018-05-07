package gof.ray.State.test;

/**
 * 状态1对象
 */
class StatusOne implements Status {

    private final Value value = Value.ONE;

    /**
     * 获取状态1对象的标志性字段值，并通过回调更改Switcher对象的当前状态
     * 
     * @param switcher
     * @return
     */
    @Override
    public Value getStatusValue(Switcher switcher) {
        Value val = this.value;
        switcher.setCurrent(switcher.getTwo());
        return val;
    }

    @Override
    public void action(Switcher switcher) {

    }
}
