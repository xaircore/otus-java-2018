package com.xairlab.otus.hibernate.connection;

import org.hibernate.SessionFactory;

public interface DBConnection {

    SessionFactory getConnection();

    void close();
}
