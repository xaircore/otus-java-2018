package com.xairlab.otus.di.connection;

import org.hibernate.SessionFactory;

public interface DBConnection {

    SessionFactory getConnection();

    void close();
}
