package gof.ray.Composite.Sample;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * 文件夹类（目录类）
 * */
public class Directory extends Entry {
    
    private String name;                    // 文件夹的名字
    private ArrayList<Entry> directory = new ArrayList<Entry>();      // 文件夹中目录条目的集合
    
    public Directory(String name) {         // 构造函数
        this.name = name;
    }
    
    @Override
    public String getName() {               // 获取名字
        return name;
    }
    
    @Override
    public int getSize() {                  // 获取大小
        int size = 0;
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            size += entry.getSize();
        }
        return size;
    }
    
    @Override
    public Entry add(Entry entry) {         // 增加目录条目
        directory.add(entry);
        return this;
    }
    
    @Override
    protected void printList(String prefix) {       // 显示目录条目一览
        System.out.println(prefix + "/" + this.toString());
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.printList(prefix + "/" + this.name);
        }
    }
}
