package gof.ray.Composite.Sample;

/**
 * 文件夹、文件基类
 */
public abstract class Entry {
    public abstract String getName(); // 获取名字

    public abstract int getSize(); // 获取大小

    // 加入目录条目
    public Entry add(Entry entry) throws Exception {
        throw new Exception("父类方法，不实现，直接异常");
    }

    public void printList() { // 为一览加上前缀并显示目录条目一览
        printList("");
    }

    // 为一览加上前缀
    protected abstract void printList(String prefix);

    public String toString() { // 显示代表类的文字
        return getName() + " (" + getSize() + ")";
    }
}
