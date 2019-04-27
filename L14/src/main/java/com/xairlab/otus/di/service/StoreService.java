package com.xairlab.otus.di.service;

import java.util.List;

public interface StoreService<T> {

    void save(T objectData);

    T load(long id, Class<T> clazz);

    List<T> all();
}
