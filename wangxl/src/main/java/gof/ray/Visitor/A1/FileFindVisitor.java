package gof.ray.Visitor.A1;

import java.util.Iterator;
import java.util.ArrayList;

public class FileFindVisitor extends Visitor {
    private String filetype;
    private ArrayList<Entry> found = new ArrayList<Entry>();
    public FileFindVisitor(String filetype) {           // 指定.后面的文件后缀名，如".txt"
        this.filetype = filetype;
    }
    public Iterator<Entry> getFoundFiles() {                   // 获取已经找到的文件
        return found.iterator();
    }
    public void visit(File file) {                  // 在访问文件时被调用
        if (file.getName().endsWith(filetype)) {
            found.add(file);
        }
    }
    public void visit(Directory directory) {   // 在访问文件夹时被调用
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this);
        }
    }
}
