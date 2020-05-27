package com.lacussoft.utils;

import java.util.Properties;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final Configuration CONFIGURATION = new Configuration().configure();
    private static final Properties PROPERTIES = CONFIGURATION.getProperties();
    private static final ServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().applySettings(PROPERTIES).build();

    private HibernateUtil() {}
    
    public static SessionFactory getSessionFactory() {
        return CONFIGURATION.buildSessionFactory(REGISTRY);
    }
}
