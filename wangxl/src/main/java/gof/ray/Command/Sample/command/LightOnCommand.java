package gof.ray.Command.Sample.command;

import gof.ray.Command.Sample.receiver.Receiver;

public class LightOnCommand extends Command {

    // 对哪个Receiver类进行命令处理
    private Receiver receiver;

    // 构造函数传递接收者
    public LightOnCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    // 必须实现一个命令
    public void execute() {
        // 业务处理
        this.receiver.turnON();
    }
}
