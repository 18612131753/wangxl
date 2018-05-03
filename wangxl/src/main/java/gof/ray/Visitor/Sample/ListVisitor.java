package gof.ray.Visitor.Sample;

import java.util.Iterator;

public class ListVisitor extends Visitor {

    private String currentdir = ""; // 当前访问的文件夹的名字

    @Override
    public void visit(File file) { // 在访问文件时被调用
        System.out.println("文件：" + currentdir + "/" + file.toString());
    }

    @Override
    public void visit(Directory directory) { // 在访问文件夹时被调用
        System.out.println("文件夹：" + currentdir + "/" + directory.toString());
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator<Entry> it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.accept(this);
        }
        currentdir = savedir;
    }
}
