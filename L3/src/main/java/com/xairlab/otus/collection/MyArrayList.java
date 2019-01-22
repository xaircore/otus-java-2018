package com.xairlab.otus.collection;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private final int capacity = 5;
    private int size = 0;
    private Object[] data;

    public MyArrayList() {
        data = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }
        for (Object item : data) {
            if (o.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size() == data.length) {
            levelUp();
        }
        data[size] = t;
        size++;
        return true;
    }

    private void levelUp() {
        int newCapacity = data.length * capacity;
        data = Arrays.copyOf(data, newCapacity, Object[].class);
    }

    @Override
    public boolean remove(Object o) {
        int objectIndex = indexOf(o);
        if (objectIndex == -1) {
            return false;
        }
        for (int i = objectIndex; i < size(); i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int match = 0;
        for (Object item : c) {
            if (contains(item)) {
                match++;
            }
        }
        return match == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object item : c) {
            result |= remove(item);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            Object current = data[i];
            if (!c.contains(current)) {
                remove(current);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        data[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        T element = (T) data[index];
        for (int i = index; i < size(); i++) {
            data[i] = data[i + 1];
        }
        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        int objectIndex = -1;
        for (int i = 0; i < size(); i++) {
            if (data[i] == o) {
                objectIndex = i;
                break;
            }
        }
        return objectIndex;
    }

    @Override
    public int lastIndexOf(Object o) {
        int objectIndex = -1;
        for (int i = size() - 1; i > 0; i--) {
            if (data[i] == o) {
                objectIndex = i;
                break;
            }
        }
        return objectIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
