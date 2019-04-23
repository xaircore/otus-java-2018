package com.xairlab.otus.hibernate.service;

public interface StoreService<T> {

    void save(T objectData);

    T load(long id, Class<T> clazz);
}
