package gof.ray.Proxy.Sample;

public class Printer implements Printable {
    private String name;

    public Printer() {
        this.heavyJob("正在生成Printer的实例");
    }

    public Printer(String name) { // 构造函数
        this.name = name;
        this.heavyJob("正在生成Printer的实例(" + name + ")");
    }

    @Override
    public void setPrinterName(String name) { // 设置名字
        this.name = name;
    }

    public String getPrinterName() { // 获取名字
        return name;
    }

    @Override
    public void print(String string) { // 显示带打印机名字的文字
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }

    // 重活（即非常耗时的工作）
    private void heavyJob(String msg) {
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.println("结束。");
    }
}
