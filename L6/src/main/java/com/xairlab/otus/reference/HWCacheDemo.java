package com.xairlab.otus.reference;

public class HWCacheDemo {

    public static void main(String[] args) {

        HwCache<Integer, Integer> cache = new MyCache<>();

        HwListener<Integer, Integer> echo = (key, value, action) -> System.out.println("key: " + key + ", value: " + value + ", action: " + action);

        cache.addListener(echo);
        cache.put(1, 1);
        cache.put(1, 1);
        cache.get(1);
        cache.remove(1);

        cache.get(1);
        cache.removeListener(echo);
    }
}
