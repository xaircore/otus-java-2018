package com.xairlab.otus.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Connection implements DBConnection {

    private static final String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public H2Connection() throws SQLException {
        connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
