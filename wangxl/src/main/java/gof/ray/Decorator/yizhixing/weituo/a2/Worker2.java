package gof.ray.Decorator.yizhixing.weituo.a2;

public class Worker2 extends Worker{

    Worker1 worker1 = new Worker1();
    
    //委托
    @Override
    public void method(){
        worker1.method();
    }
}
