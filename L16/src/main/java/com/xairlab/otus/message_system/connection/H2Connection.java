package com.xairlab.otus.message_system.connection;

import com.xairlab.otus.message_system.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class H2Connection implements DBConnection {

    private final SessionFactory connection;

    public H2Connection() {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        connection = configuration.buildSessionFactory(builder.build());
    }

    @Override
    public SessionFactory getConnection() {
        return connection;
    }

    @Override
    public void close() {
        connection.close();
    }
}
