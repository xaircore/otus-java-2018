package com.xairlab.otus.di.service;

import java.util.List;

public interface StoreService<T> {

    void save(T objectData);

    List<T> all();

    T findByName(String name);
}
