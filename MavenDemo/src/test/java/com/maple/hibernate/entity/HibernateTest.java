package com.maple.hibernate.entity;

import com.maple.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by maple on 2014/5/11.
 */
public class HibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        System.out.println("init");
        /**
         * Method 1
         */
//        Configuration configuration = new Configuration().configure();
//        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        session = sessionFactory.openSession();
//        transaction = session.beginTransaction();
        /**
         * Method 2
         */
        session = HibernateUtil.getSession();
    }

    @After
    public void destroy() {
        System.out.println("destroy");

        /**
         * Method 1
         */
//        transaction.commit();
//        session.close();
//        sessionFactory.close();

        /**
         * Method 2
         */
        HibernateUtil.closeSession();
    }

    @Test
    public void test() {
        News news = new News("Java", "Maple", new Date());
        session.save(news);
        System.out.println(news);
    }

    @Test
    public void testSessionCache() {
        News news1 = (News) session.get(News.class, 1);
        System.out.println(news1);

        News news2 = (News) session.get(News.class, 2);
        System.out.println(news2);
    }

    /**
     * flush: 使数据表中的记录如session缓存中的对象的状态保持一致
     * 1. 在transaction的commit()方法中，会先调用session的flush()方法
     * 2. flush()方法会可能会发送SQL语句，但不会提交事务
     * 3.
     */
    @Test
    public void testSessionFlush() {
        News news = (News) session.get(News.class, 1);
        news.setAuthor("TEST");

    }

    @Test
    public void testOneToOne() {
        Body body = new Body();
        Heart heart = new Heart();
        body.setHeart(heart);
        session.save(heart);
        session.save(body);
    }

    @Test
    public void testMany2One() {
        Customer customer = new Customer();
        customer.setCustomerName("AAA");

        Order order1 = new Order();
        order1.setOrderName("ORDER-1");

        Order order2 = new Order();
        order2.setOrderName("ORDER-2");

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        // Better save customer first, otherwise more update statements generated after insert statements
        session.save(customer);
        session.save(order1);
        session.save(order2);

        System.out.println(customer);
        System.out.println(order1);
        System.out.println(order2);
    }

    @Test
    public void testMany2OneGet() {
        Order order = (Order) session.get(Order.class, 5);
        System.out.println(order.toString());

        Customer customer = order.getCustomer();
        System.out.println(customer);
    }

    @Test
    public void testOne2ManyGet() {
        Customer customer = (Customer) session.get(Customer.class, 2);
        System.out.println(customer);

        Set<Order> orders = customer.getOrders();
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testDelete() {
        /**
         * Can not delete if no OneToMany config in Customer entity
         */
        Customer customer = (Customer) session.get(Customer.class, 5);
        session.delete(customer);
    }


}

















