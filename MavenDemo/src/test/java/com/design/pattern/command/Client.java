package com.design.pattern.command;

import org.junit.Test;

/**
 * Created by maple on 2014/7/26.
 */
public class Client {

    @Test
    public void test1() {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setOrder(command);
        invoker.execute();
    }
}
