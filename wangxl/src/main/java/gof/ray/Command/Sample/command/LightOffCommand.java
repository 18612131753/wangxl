package gof.ray.Command.Sample.command;

import gof.ray.Command.Sample.receiver.Receiver;

//关灯命令
public class LightOffCommand extends Command {

    // 对哪个Receiver类进行命令处理
    private Receiver receiver;

    // 构造函数传递接收者
    public LightOffCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    // 必须实现一个命令
    public void execute() {
        // 业务处理
        this.receiver.turnOFF();
    }
}
