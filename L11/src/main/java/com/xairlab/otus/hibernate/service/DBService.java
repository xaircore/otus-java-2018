package com.xairlab.otus.hibernate.service;

import com.xairlab.otus.hibernate.connection.DBConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class DBService<T> implements StoreService<T> {

    private final SessionFactory connection;

    public DBService(DBConnection connection) {
        this.connection = connection.getConnection();
    }


    @Override
    public void save(T data) {
        try (Session session = connection.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(data);
            transaction.commit();
        }
    }

    @Override
    public T load(long id, Class<T> clazz) {
        try (Session session = connection.openSession()) {
            return session.get(clazz, id);
        }
    }
}
