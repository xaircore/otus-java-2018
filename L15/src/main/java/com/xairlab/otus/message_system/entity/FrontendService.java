package com.xairlab.otus.message_system.entity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FrontendService extends Addressee {

    void init();

    void getUsersAction();

    void saveUserAction(String name, int age);

    void getUsersHandler(List<User> user);

    void saveUserHandler(User user);

    List<User> getUsers();
}

