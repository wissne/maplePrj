package com.design.pattern.command;

/**
 * Created by maple on 2014/7/26.
 */
public class Invoker {
    private Command command;

    public void setOrder(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
