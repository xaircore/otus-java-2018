package com.xairlab.otus.message_system.service.db;


import com.xairlab.otus.message_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {
    private final Address address = new Address("DB");
    private final MessageSystemContext context;
    private final Session session;

    public DBServiceImpl(MessageSystemContext context) {
        this.context = context;

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        session = factory.openSession();
        init();
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setDbAddress(this.address);
    }

    @Override
    public List<User> getUsers() {
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public User saveUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(user);
        transaction.commit();
        return user;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return context.getMessageSystem();
    }
}
