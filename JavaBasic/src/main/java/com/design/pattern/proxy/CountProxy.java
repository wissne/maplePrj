package com.design.pattern.proxy;

/**
 * Created by maple on 2014/6/3.
 */
public class CountProxy implements Count{

    private CountImpl countImpl;

    public CountProxy(CountImpl countImpl) {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        System.out.println("before...");
        countImpl.queryCount();
        System.out.println("after...");
    }

    @Override
    public void updateCount() {
        System.out.println("before...");
        countImpl.updateCount();
        System.out.println("after...");
    }
}
