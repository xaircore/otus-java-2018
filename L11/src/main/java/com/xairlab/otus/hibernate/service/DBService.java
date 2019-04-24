package com.xairlab.otus.hibernate.service;

import com.xairlab.otus.hibernate.connection.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;


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
    public T load(long id, Class<T> clazz) {
        return (T) session.get(clazz, id);
    }
}
