package gof.ray.ChainOfResponsibility.Sample;

/**
 * 不处理问题
 */
public class NoSupport extends Support {
    public NoSupport(String name) {
        super(name);
    }

    // 解决问题的方法
    @Override
    protected boolean resolve(Trouble trouble) {
        return false; // 自己什么也不处理
    }
}
