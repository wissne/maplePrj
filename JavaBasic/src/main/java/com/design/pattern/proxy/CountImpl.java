package com.design.pattern.proxy;

/**
 * Created by maple on 2014/6/3.
 */
public class CountImpl implements Count {
    @Override
    public void queryCount() {
        System.out.println("Query count");
    }

    @Override
    public void updateCount() {
        System.out.println("Update count");
    }
}
