package design.ray.a1_yuanze.aa4_isp.a1;

/**
 * A依赖 接口I中的method1 method2 method3
 */
public class A {

    public void depend1(I i) {
        i.method1();
    }

    public void depend2(I i) {
        i.method2();
    }

    public void depend3(I i) {
        i.method3();
    }

}
