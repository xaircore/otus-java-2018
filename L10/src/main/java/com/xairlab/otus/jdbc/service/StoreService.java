package com.xairlab.otus.jdbc.service;

public interface StoreService<T> {

    void save(T objectData);

    <T> T load(long id, Class<T> clazz);
}
