package gof.ray.State.test;

/**
 * 类似一个开关的对象，多次调用getCurrentValue（）方法，返回值会进行有规律的切换
 *
 * 写出这几个类只是为了复习状态模式，别无它意，还有，网上的大多数状态模式的例子都不够地道，
 * 更像是其他模式，比如策略模式，因为那些例子将状态切换的代码写在了充当Context角色的类（这里的
 * 具体例子中，这个角色就是Switcher）中,结果明显是一个策略模式，然后又不完全像是策略模式，
 * 相当诡异，这也是促使自己把这个例子粘贴出来的原因。
 */
public class Switcher {
    private Status one = new StatusOne();
    private Status two = new StatusTwo();
    private volatile Status current = one;// 将当前状态预设为状态1

    /**
     * 查看当前状态
     * 
     * @return
     */
    public Status getCurrent() {
        return current;
    }

    /**
     *
     * @param 修改当前状态
     */
    public synchronized void setCurrent(Status current) {
        this.current = current;
    }

    /**
     * 获取当前状态的值
     * 
     * @return
     */
    public Value getCurrentValue() {
        return current.getStatusValue(this);
    }

    /**
     * 获取状态1
     * 
     * @return
     */
    public Status getOne() {
        return one;
    }

    /**
     * 获取状态2
     * 
     * @return
     */
    public Status getTwo() {
        return two;
    }

    public static void main(String... args) {
        Switcher s = new Switcher();
        for (int i = 0; i < 20; i++) {
            System.out.println(s.getCurrentValue());
        }
    }
}
