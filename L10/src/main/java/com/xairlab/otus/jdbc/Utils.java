package com.xairlab.otus.jdbc;

import com.xairlab.otus.jdbc.connection.DBConnection;
import com.xairlab.otus.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {

    private final Connection connection;

    public Utils(DBConnection connection) {
        this.connection = connection.getConnection();
    }

    public void listTable() throws SQLException {
        System.out.println("Данные в таблице:");
        try (PreparedStatement pst = connection.prepareStatement("select * from User")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        }
    }

    public void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table User(id long auto_increment, name varchar(50), age int)")) {
            pst.executeUpdate();
        }
    }

    public static User createUser(long id, String name, int age){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
