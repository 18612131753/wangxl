package gof.ray.Visitor.Sample;

import java.util.Iterator;

public abstract class Entry implements Element {

    public abstract String getName(); // 获取名字

    public abstract int getSize(); // 获取大小

    public Entry add(Entry entry) throws Exception { // 增加目录条目
        throw new Exception("父类方法，不实现，直接异常");
    }

    // 生成Iterator - 新增
    public Iterator<Entry> iterator() throws Exception {
        throw new Exception("父类方法，不实现，直接异常");
    }

    public String toString() { // 显示字符串
        return this.getName() + " (" + this.getSize() + ")";
    }
}