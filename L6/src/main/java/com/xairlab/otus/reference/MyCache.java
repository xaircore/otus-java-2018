package com.xairlab.otus.reference;

import java.lang.ref.SoftReference;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCache<K, V> implements HwCache<K, V> {

    static Logger logger = LoggerFactory.getLogger(MyCache.class);

    private final Map<K, SoftReference<V>> cache;
    private final List<HwListener<K, V>> listeners;

    public int getHit() {
        return hit;
    }

    private int hit = 0;

    public int getMiss() {
        return miss;
    }

    private int miss = 0;

    public MyCache() {
        cache = new HashMap<>();
        listeners = new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        logger.debug("put value " + value + " to cache by key " + key);
        cache.put(key, new SoftReference<>(value));
        applyListener(key, value, "put to cache");
    }

    @Override
    public void remove(K key) {
        logger.debug("remove value from cache by key " + key);
        V item = cache.get(key).get();
        applyListener(key, item, "remove from cache");
    }

    @Override
    public V get(K key) {
        logger.debug("get value from cache by key " + key);
        if (cache.containsKey(key)) {
            V item = cache.get(key).get();
            if (item != null) {
                hit++;
                logger.debug("cache hit");
                applyListener(key, item, "cache hit");
                return item;
            }
        }
        miss++;
        logger.debug("cache miss");
        applyListener(key, null, "cache miss");
        return null;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    private void applyListener(K key, V value, String action) {
        listeners.forEach(listener -> {
            logger.debug("use listener " + listener.getClass().getSimpleName());
            listener.notify(key, value, action);
        });
    }
}
