package com.xairlab.otus.hibernate.service;

import com.xairlab.otus.hibernate.entity.User;

public class UserService {

    StoreService<User> service;

    public UserService(StoreService<User> service) {
        this.service = service;
    }

    public void save(User user) {
        service.save(user);
    }

    public User load(long id) {
        return service.load(id, User.class);
    }
}
