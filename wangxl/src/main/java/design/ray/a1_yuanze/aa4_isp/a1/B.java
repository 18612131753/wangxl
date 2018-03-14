package design.ray.a1_yuanze.aa4_isp.a1;

public class B implements I {

    @Override
    public void method1() {
        System.out.println("类B实现接口I的方法1");
    }

    @Override
    public void method2() {
        System.out.println("类B实现接口I的方法2");
    }

    @Override
    public void method3() {
        System.out.println("类B实现接口I的方法3");
    }

    // 对于类B来说，method4和method5不是必需的，但是由于接口 I 中有这两个方法，
    // 所以在实现过程中即使这两个方法的方法体为空，也要将这两个没有作用的方法进行实现。
    @Override
    public void method4() {
    }

    @Override
    public void method5() {
    }

}
