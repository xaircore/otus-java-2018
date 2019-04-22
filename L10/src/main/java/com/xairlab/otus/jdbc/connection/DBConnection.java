package com.xairlab.otus.jdbc.connection;

import java.sql.Connection;

public interface DBConnection {

    Connection getConnection();

    void close();
}
