package com.maple.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by maple on 2014/5/14.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static Session session;

    private static Transaction transaction;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        if (session.isOpen()) {
            return session;
        }
        else {
            session = sessionFactory.openSession();
            return session;
        }
    }

    public static void closeSession() {
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
