package com.design.pattern.proxy;

/**
 * Created by maple on 2014/7/26.
 */
public class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("Tank is moving...");
    }
}
