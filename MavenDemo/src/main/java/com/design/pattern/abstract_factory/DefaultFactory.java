package com.design.pattern.abstract_factory;

/**
 * Created by maple on 2014/7/26.
 */
public class DefaultFactory extends AbstractFactory {

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }

    @Override
    public Weapon createWeapon() {
        return new Gun();
    }

    @Override
    public Food createFood() {
        return new Apple();
    }
}
