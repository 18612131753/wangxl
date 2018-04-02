package gof.ray.Visitor.Sample;

/**
 * 表示数据结构的接口，他接受访问者的访问
 * */
public interface Element {
    
    public abstract void accept(Visitor v);
    
}
