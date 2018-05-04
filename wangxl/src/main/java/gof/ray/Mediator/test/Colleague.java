package gof.ray.Mediator.test;

public abstract class Colleague {
    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    
    //获取消息
    public abstract void getMessage(String message) ;
    //发送消息
    public abstract void contact(String message) ;
}
