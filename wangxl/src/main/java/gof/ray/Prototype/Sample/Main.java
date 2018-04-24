package gof.ray.Prototype.Sample;

import gof.ray.Prototype.Sample.framework.*;

public class Main {
    public static void main(String[] args) {

        // 准备
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("upen", upen); //强信息
        manager.register("mbox", mbox); //星号箱
        manager.register("sbox", sbox);  //斜线箱

        // 生成
        Product p1 = manager.create("upen");
        p1.use("Hello, world.");
        Product p2 = manager.create("mbox");
        p2.use("Hello, world1.");
        Product p3 = manager.create("sbox");
        p3.use("Hello, world2.");
    }
}
