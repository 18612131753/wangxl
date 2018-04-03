package gof.ray.ChainOfResponsibility.Sample;

/**
 * 解决问题的抽象类
 */
public abstract class Support {
    private String name; // 解决问题的实例的名字
    private Support next; // 要推卸给的对象

    public Support(String name) { // 生成解决问题的实例
        this.name = name;
    }

    public Support setNext(Support next) { // 设置要推卸给的对象
        this.next = next;
        return next;
    }

    // 解决问题的步骤
    public void support(Trouble trouble) {
        if (resolve(trouble)) {
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            fail(trouble);
        }
    }

    @Override
    public String toString() { // 显示字符串
        return "[" + name + "]";
    }

    // 解决问题的方法
    protected abstract boolean resolve(Trouble trouble);

    // 解决
    protected void done(Trouble trouble) {
        System.out.println(trouble + " is resolved by " + this + ".");
    }

    // 未解决
    protected void fail(Trouble trouble) {
        System.out.println(trouble + " cannot be resolved.");
    }
}
