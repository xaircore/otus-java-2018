package com.xairlab.otus.hibernate.connection;

import com.xairlab.otus.hibernate.entity.AddressDataSet;
import com.xairlab.otus.hibernate.entity.PhoneDataSet;
import com.xairlab.otus.hibernate.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class H2Connection implements DBConnection {

    private final SessionFactory connection;

    public H2Connection() {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

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
