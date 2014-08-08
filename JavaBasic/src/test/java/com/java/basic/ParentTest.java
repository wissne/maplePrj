package com.java.basic;

import org.junit.Test;

/**
 * Created by maple on 2014/6/1.
 */
public class ParentTest {

    @Test
    public void testFirst() {
        Child child = new Child();
        child.setName("test");
        System.out.println(child.getName());

        Child child1 = child;

        System.out.println(child.equals(child1));
    }

}
