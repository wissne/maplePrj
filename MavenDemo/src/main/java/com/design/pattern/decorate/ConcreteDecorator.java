package com.design.pattern.decorate;

/**
 * Created by maple on 2014/7/26.
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator() {
    }

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        this.addedOperation();
        super.operation();
    }

    public void addedOperation() {
        System.out.println("Night");
    }
}
