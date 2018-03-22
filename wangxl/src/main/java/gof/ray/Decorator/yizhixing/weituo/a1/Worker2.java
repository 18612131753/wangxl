package gof.ray.Decorator.yizhixing.weituo.a1;

public class Worker2 {

    Worker1 worker1 = new Worker1();
    
    //委托
    public void method(){
        worker1.method();
    }
}
