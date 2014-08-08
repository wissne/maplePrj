package com.design.pattern.proxy;

/**
 * Created by maple on 2014/7/26.
 */
public class TankTimeProxy implements Movable {

    private Movable target;

    public TankTimeProxy(Movable target) {
        super();
        this.target = target;
    }
    @Override
    public void move() {
        long timeStart = System.currentTimeMillis();
        System.out.println("Time start=" + timeStart);
        target.move();
        long timeEnd = System.currentTimeMillis();
        System.out.println("Time end=" + timeEnd);
        System.out.println("Time ellipse=" + (timeEnd - timeStart));
    }
}
