package com.design.pattern.proxy;

/**
 * Created by maple on 2014/7/26.
 */
public class TankLogProxy implements Movable {

    private Movable target;
    
    public TankLogProxy(Movable target) {
        this.target = target;
    }
    @Override
    public void move() {
        System.out.println("Start move...");
        target.move();
        System.out.println("End move...");
    }
}
