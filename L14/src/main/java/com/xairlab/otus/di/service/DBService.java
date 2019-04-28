package com.xairlab.otus.di.service;

import com.xairlab.otus.di.connection.H2Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBService<T> implements StoreService<T> {

    private final Session session;

    public DBService() {
        session = new H2Connection().getConnection().openSession();
    }

    @Override
    public void save(T data) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.saveOrUpdate(data);
        transaction.commit();
    }

    @Override
    public List<T> all() {
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public T findByName(String name) {
        Query query = session.createQuery("from User where name=:name");
        query.setParameter("name", name);
        return (T) query.getSingleResult();
    }
}
