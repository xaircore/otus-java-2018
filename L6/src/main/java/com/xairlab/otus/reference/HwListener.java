package com.xairlab.otus.reference;

public interface HwListener<K, V> {
    void notify(K key, V value, String action);
}
