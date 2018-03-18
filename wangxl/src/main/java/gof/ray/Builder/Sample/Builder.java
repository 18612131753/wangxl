package gof.ray.Builder.Sample;

public abstract class Builder {
    //创建标题
    public abstract void makeTitle(String title);

    //创建字符串
    public abstract void makeString(String str);
    
    //创建条目项目
    public abstract void makeItems(String[] items);

    public abstract void close();
}
