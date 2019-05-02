package com.xairlab.otus.message_system.entity;

import java.util.List;

public interface DBService extends Addressee {

    void init();

    List<User> getUsers();

    User saveUser(String name, int age);
}
