package gof.ray.Command.Sample.Invoker;

import gof.ray.Command.Sample.command.Command;

/**
 * 发动者
 */
public class Invoker {

    public void action(Command command) {
        System.out.println("----- 开始执行命令");
        command.execute();
    }
}
