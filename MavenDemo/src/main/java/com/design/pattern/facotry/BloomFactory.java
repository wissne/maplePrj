package com.design.pattern.facotry;

/**
 * Created by maple on 2014/7/26.
 */
public class BloomFactory extends VehicleFactory {
    @Override
    public Movable create() {
        return new Bloom();
    }
}
