package gof.ray.Mediator.test;

public class ColleagueB extends Colleague {

    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMessage(String message) {
        System.out.println("同事B " + name + " 获得信息:" + message);
    }

    // 同事B与中介者通信
    public void contact(String message) {
        mediator.contact(message, this);
    }
}
