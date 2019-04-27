package com.xairlab.otus.jetty.connection;

import org.hibernate.SessionFactory;

public interface DBConnection {

    SessionFactory getConnection();

    void close();
}
