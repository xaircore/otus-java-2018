package com.xairlab.otus.jetty.service;

import java.util.List;

public interface StoreService<T> {

    void save(T objectData);

    T load(long id, Class<T> clazz);

    List<T> all();
}
