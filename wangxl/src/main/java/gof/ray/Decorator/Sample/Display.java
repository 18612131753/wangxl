package gof.ray.Decorator.Sample;

public abstract class Display {
    // 获取横向字符数
    public abstract int getColumns();

    // 获取字符串行数
    public abstract int getRows();

    // 获取第row行的字符串
    public abstract String getRowText(int row);

    public void show() { // 全部显示
        for (int i = 0; i < this.getRows(); i++) {
            System.out.println(this.getRowText(i));
        }
    }
}
