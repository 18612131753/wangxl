package gof.ray.Mediator.test;

//定义抽象Mediator,可以与同时们进行联络
public abstract class Mediator {
    
    // 发送信息方式，谁发送了什么内容
    public abstract void contact(String content, Colleague colleague);
    
}
