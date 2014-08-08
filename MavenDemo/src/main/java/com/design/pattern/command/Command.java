package com.design.pattern.command;

/**
 * Created by maple on 2014/7/26.
 */
public abstract class Command {
    protected Receiver receiver;

    protected Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute();
}