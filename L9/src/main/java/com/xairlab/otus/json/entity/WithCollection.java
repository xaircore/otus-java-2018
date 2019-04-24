package com.xairlab.otus.json.entity;

import java.util.Collection;

public class WithCollection<E> {

    Collection<E> collection;

    public WithCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "WithCollection{" +
                "collection=" + collection +
                '}';
    }
}
