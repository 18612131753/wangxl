package gof.ray.Visitor.Sample;

/**
 * 访问者的抽象类，它访问文件和文件夹
 * */
public abstract class Visitor {
    public abstract void visit(File file);

    public abstract void visit(Directory directory);
}
