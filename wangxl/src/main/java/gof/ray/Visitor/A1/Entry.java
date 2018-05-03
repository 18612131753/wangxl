package gof.ray.Visitor.A1;
import java.util.Iterator;

public abstract class Entry implements Element {
    public abstract String getName();                                   // 获取名字
    public abstract int getSize();                                      // 获取大小
    public Entry add(Entry entry) throws Exception {       // 增加目录条目
        throw new Exception();
    }
    public Iterator<Entry> iterator() throws Exception {    // 生成Iterator
        throw new Exception();
    }
    public String toString() {                                          // 显示字符串
        return getName() + " (" + getSize() + ")";
    }
}
