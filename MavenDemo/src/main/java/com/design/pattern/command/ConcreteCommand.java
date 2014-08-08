package com.design.pattern.command;

/**
 * Created by maple on 2014/7/26.
 */
public class ConcreteCommand extends Command {

    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.execute();
    }
}
