package com.design.pattern.abstract_factory;

import org.junit.Test;

/**
 * Created by maple on 2014/7/26.
 */
public class AbstractFactoryTest {
    @Test
    public void test1() {
        AbstractFactory factory = new DefaultFactory();
        Vehicle vehicle = factory.createVehicle();
        Weapon weapon = factory.createWeapon();
        Food food = factory.createFood();

        vehicle.run();
        weapon.shoot();
        food.printName();


    }
}
