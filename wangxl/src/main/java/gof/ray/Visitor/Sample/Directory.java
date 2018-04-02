package gof.ray.Visitor.Sample;

import java.util.Iterator;
import java.util.ArrayList;

//文件夹类
public class Directory extends Entry {
    private String name; // 文件夹名字
    private ArrayList<Entry> dir = new ArrayList<Entry>(); // 目录条目集合

    public Directory(String name) { // 构造函数
        this.name = name;
    }

    @Override
    public String getName() { // 获取名字
        return name;
    }

    @Override
    public int getSize() { // 获取大小
        int size = 0;
        Iterator<Entry> it = dir.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) { // 增加目录条目
        dir.add(entry);
        return this;
    }

    @Override
    public Iterator<Entry> iterator() { // 生成Iterator
        return dir.iterator();
    }

    // 接受访问者的访问
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
