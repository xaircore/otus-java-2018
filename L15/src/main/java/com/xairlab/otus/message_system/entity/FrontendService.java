package com.xairlab.otus.message_system.entity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FrontendService extends Addressee {

    void init();

    void getUsers();

    void addUsers(List<User> user);

    void addUser(User user);

    void saveUser(String name, int age);

    List<User> getFrontendUsers();
}

