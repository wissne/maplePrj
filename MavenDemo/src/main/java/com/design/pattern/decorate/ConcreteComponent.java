package com.design.pattern.decorate;

/**
 * Created by maple on 2014/7/26.
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("Operate");
    }
}
