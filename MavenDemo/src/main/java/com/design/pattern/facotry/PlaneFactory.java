package com.design.pattern.facotry;

/**
 * Created by maple on 2014/7/26.
 */
public class PlaneFactory extends VehicleFactory {
    @Override
    public Movable create() {
        return new Plane();
    }
}
