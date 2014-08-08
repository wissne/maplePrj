package com.design.pattern.abstract_factory;

/**
 * Created by maple on 2014/7/26.
 */
public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();
    public abstract Weapon createWeapon();
    public abstract Food createFood();
}
