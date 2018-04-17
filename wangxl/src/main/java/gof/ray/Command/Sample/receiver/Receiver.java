package gof.ray.Command.Sample.receiver;

//通用Receiver类
public class Receiver {
    public void turnON() {
        System.out.println("OPEN - 执行开灯操作~");
    }

    public void turnOFF() {
        System.out.println("CLOSE - 执行关灯操作~");
    }
}