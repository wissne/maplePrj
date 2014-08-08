package com.design.pattern.proxy;

import com.design.pattern.proxy.cglib.BookFacadeCglibProxy;
import com.design.pattern.proxy.cglib.BookFacadeImplCglib;
import com.design.pattern.proxy.facade.BookFacade;
import com.design.pattern.proxy.facade.BookFacadeImpl;
import com.design.pattern.proxy.facade.BookFacadeProxy;
import org.junit.Test;

/**
 * Created by maple on 2014/6/3.
 */
public class TestProxy {

    @Test
    public void testCount() {
        CountImpl countImpl = new CountImpl();
        CountProxy proxy = new CountProxy(countImpl);
        proxy.queryCount();
        proxy.updateCount();
    }

    @Test
    public void testBookFacade() {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookFacade.addBook("Spring in Action");
    }

    @Test
    public void testCglibProxy() {
        BookFacadeCglibProxy proxy = new BookFacadeCglibProxy();
        BookFacadeImplCglib bookFacadeImplCglib = (BookFacadeImplCglib) proxy.getInstance(new BookFacadeImplCglib());
        bookFacadeImplCglib.addBook("Grails on Groovy");
    }
}
