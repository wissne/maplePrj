package com.design.pattern.decorate;

import org.junit.Test;

/**
 * Created by maple on 2014/7/26.
 */
public class DecoratorTest {
    @Test
    public void test1() {
        Component component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator(component);
        decorator.operation();
    }
}
