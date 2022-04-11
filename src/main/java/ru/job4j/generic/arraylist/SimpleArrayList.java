package ru.job4j.generic.arraylist;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;
    private int i;
    private int expectedModCount;


    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
}

    @Override
    public void add(T value) {
        if (size + 1 > container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
}

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        expectedModCount = modCount;
        i = 0;
        return new Iterator<T>() {
                        @Override
                        public boolean hasNext() {
                            if (expectedModCount != modCount) {
                                throw new ConcurrentModificationException();
                            }
                            return i < size;
                        }

                        @Override
                        public T next() {
                            if (!hasNext()) {
                                throw new NoSuchElementException();
                            }
                            return container[i++];
                        }
        };
    }
}