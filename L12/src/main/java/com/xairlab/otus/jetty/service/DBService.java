package com.xairlab.otus.jetty.service;

import com.xairlab.otus.jetty.connection.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import java.util.List;


public class DBService<T> implements StoreService<T> {

    private final Session session;

    public DBService(DBConnection connection) {
        session = connection.getConnection().openSession();
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
