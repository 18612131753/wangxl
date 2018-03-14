package design.ray.a1_yuanze.aa4_isp.a2;

class A {
    public void depend1(I1 i) {
        i.method1();
    }

    public void depend2(I2 i) {
        i.method2();
    }

    public void depend3(I2 i) {
        i.method3();
    }
}
