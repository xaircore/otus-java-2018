package com.xairlab.otus.jetty.service;

import com.xairlab.otus.jetty.entity.User;

import java.util.List;

public class UserService {

    private final StoreService<User> service;

    public UserService(StoreService<User> service) {
        this.service = service;
    }

    public void save(User user) {
        service.save(user);
    }

    public User load(long id) {
        return service.load(id, User.class);
    }

    public List<User> all(){
        return service.all();
    }
}
