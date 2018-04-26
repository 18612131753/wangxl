package gof.ray.Builder.Sample;

/**
 * 建造者，定义决定文档结构的方法的抽象类
 */
public abstract class Builder {
    // 创建标题
    public abstract void makeTitle(String title);

    // 创建二级标题
    public abstract void makeString(String str);

    // 创建标题下的条目内容
    public abstract void makeItems(String[] items);

    public abstract void close();
}
