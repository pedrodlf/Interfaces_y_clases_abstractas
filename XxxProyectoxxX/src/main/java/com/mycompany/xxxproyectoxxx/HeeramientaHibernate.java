/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xxxproyectoxxx;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Pedro DLF
 */
public class HeeramientaHibernate {

    private static final SessionFactory sessionFactory;

    static {

        try {

            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            StandardServiceRegistryBuilder ssrb
                    = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(ssrb.build());

        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Creacion del session factory inicial Fallida" + ex.getMessage());
            throw new ExceptionInInitializerError(ex);

        }

    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;

    }

    public static Session getSession() throws HibernateException {

        Session session;

        session = getSessionFactory().openSession();

        return session;
    }

}
