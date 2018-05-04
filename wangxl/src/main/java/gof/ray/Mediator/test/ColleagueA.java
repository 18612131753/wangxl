package gof.ray.Mediator.test;

public class ColleagueA extends Colleague {

    // 具体同事类继承自Colleague,此刻就可以与中介者mediator进行通信了
    public ColleagueA(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("同事A " + name + " 获得信息:" + message);
    }

    // 同事A与中介者通信
    @Override
    public void contact(String message) {
        mediator.contact(message, this);
    }
}
