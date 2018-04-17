package gof.ray.Command.Sample;

import gof.ray.Command.Sample.Invoker.Invoker;
import gof.ray.Command.Sample.command.Command;
import gof.ray.Command.Sample.command.LightOffCommand;
import gof.ray.Command.Sample.command.LightOnCommand;
import gof.ray.Command.Sample.receiver.Receiver;

public class Clinet {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker();

        Command turnOnLight = new LightOnCommand(receiver);
        Command turnOffLight = new LightOffCommand(receiver);

        invoker.action(turnOnLight);
        invoker.action(turnOffLight);
    }
}
