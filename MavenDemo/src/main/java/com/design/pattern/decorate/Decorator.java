package com.design.pattern.decorate;

/**
 * Created by maple on 2014/7/26.
 */
public class Decorator implements Component {
    private Component component;

    public Decorator() {
    }

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
