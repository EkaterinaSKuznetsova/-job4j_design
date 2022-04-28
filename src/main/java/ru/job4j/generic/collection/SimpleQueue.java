package ru.job4j.generic.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        T rsl = null;
        if (in.getSize() == 0 && out.getSize() == 0) {
            throw new NoSuchElementException();
        }
        if (out.getSize() == 0) {
            while (in.getSize() > 0) {
                out.push(in.pop());
            }
        }
        if (out.getSize() != 0) {
            rsl = out.pop();
        }

        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
