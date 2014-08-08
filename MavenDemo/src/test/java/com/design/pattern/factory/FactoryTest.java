package com.design.pattern.factory;

import com.design.pattern.facotry.BloomFactory;
import com.design.pattern.facotry.Movable;
import com.design.pattern.facotry.PlaneFactory;
import com.design.pattern.facotry.VehicleFactory;
import org.junit.Test;

/**
 * Created by maple on 2014/7/26.
 */
public class FactoryTest {
    @Test
    public void test1() {
        VehicleFactory vehicleFactory = new BloomFactory();
        Movable movable = vehicleFactory.create();
        movable.run();

        vehicleFactory = new PlaneFactory();
        movable = vehicleFactory.create();
        movable.run();
    }
}
