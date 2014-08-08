package com.design.pattern.proxy.facade;

/**
 * Created by maple on 2014/6/3.
 */
public class BookFacadeImpl implements BookFacade {

    @Override
    public void addBook(String s) {
        System.out.println(s + " book added...");
    }
}
