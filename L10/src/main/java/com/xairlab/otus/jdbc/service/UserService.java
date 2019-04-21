package com.xairlab.otus.jdbc.service;

import com.xairlab.otus.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService implements StoreService<User> {

    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User objectData) {
        User stub = load(objectData.getId(), User.class);
        if (stub == null) {
            try (PreparedStatement pst = connection.prepareStatement("insert into User(id, name, age) values(?, ?, ?)")) {
                pst.setLong(1, objectData.getId());
                pst.setString(2, objectData.getName());
                pst.setInt(3, objectData.getAge());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement pst = connection.prepareStatement("update User set name = ?, age = ? where id = ?")) {
                pst.setString(1, objectData.getName());
                pst.setInt(2, objectData.getAge());
                pst.setLong(3, objectData.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        try (PreparedStatement pst = connection.prepareStatement("select * from User where id = ?")) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return (T) new User(rs.getLong(1), rs.getString(2), rs.getInt(3));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
