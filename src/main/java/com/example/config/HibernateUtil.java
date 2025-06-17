package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "org.h2.Driver");
        settings.put("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        settings.put("hibernate.connection.username", "sa");
        settings.put("hibernate.connection.password", "");
        settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.show_sql", true);
        settings.put("hibernate.format_sql", true);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        return new MetadataSources(serviceRegistry)
                //.addAnnotatedClass(Persona.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
