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

    // 解决问题的步骤,能解决，就解决，不能解决，往下继续走，最后失败
    public void support(Trouble trouble) {
        if (this.resolve(trouble)) {
            this.done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            this.fail(trouble);
        }
    }

    @Override
    public String toString() { // 显示字符串
        return "[" + name + "]";
    }

    // 决定问题，看看是否能解决
    protected abstract boolean resolve(Trouble trouble);

    // 解决
    protected void done(Trouble trouble) {
        System.out.println(trouble.toString() + " is resolved by " + this.toString() + ".");
    }

    // 未解决
    protected void fail(Trouble trouble) {
        System.out.println(trouble + " cannot be resolved.");
    }
}
