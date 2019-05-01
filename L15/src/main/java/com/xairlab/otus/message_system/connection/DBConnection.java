package com.xairlab.otus.message_system.connection;

import org.hibernate.SessionFactory;

public interface DBConnection {

    SessionFactory getConnection();

    void close();
}
