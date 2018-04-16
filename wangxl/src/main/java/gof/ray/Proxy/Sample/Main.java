package gof.ray.Proxy.Sample;

public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("代理");
        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.setPrinterName("本人");
        System.out.println("现在的名字是" + p.getPrinterName() + "。");
        p.print("Hello, world.");
    }
}
