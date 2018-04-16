package gof.ray.Proxy.Sample;

public interface Printable {
    // 设置名字
    public abstract void setPrinterName(String name);

    // 获取名字
    public abstract String getPrinterName();

    // 显示文字（打印输出）
    public abstract void print(String string);
}
