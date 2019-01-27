package com.xairlab.otus.collection;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private class MyArrayListIterator implements ListIterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index != size();
        }

        @Override
        public T next() {
            T element = (T) data[index];
            index++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return index >= 0;
        }

        @Override
        public T previous() {
            return (T) data[index - 1];
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new RuntimeException();
        }

        @Override
        public void set(T t) {
            int toSet = index;
            if (index != 0) {
                toSet = index - 1;
            }
            data[toSet] = t;
        }

        @Override
        public void add(T t) {
            data[index] = t;
            index++;
        }
    }

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
        throw new RuntimeException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size(), Object[].class);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException();
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
        throw new RuntimeException();
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
        throw new RuntimeException();
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
        return new MyArrayListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new RuntimeException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new RuntimeException();
    }
}
