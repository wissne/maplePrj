package com.design.pattern.proxy;

import org.junit.Test;

/**
 * Created by maple on 2014/7/26.
 */
public class TankTest {

    @Test
    public void test1() {
        Tank tank = new Tank();
        Movable movable = new TankTimeProxy(tank);
        movable.move();
    }

    @Test
    public void test2() {
        Tank tank = new Tank();
        Movable movable1 = new TankTimeProxy(tank);
        Movable movable2 = new TankLogProxy(movable1);
        movable2.move();
    }
}
