package ru.job4j.generic.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        int sizeOut = size;
        T rsl;
        while (size > 0) {
            out.push(in.pop());
            size--;
        }
        rsl = out.pop();
        while (size < sizeOut - 1) {

            in.push(out.pop());
            size++;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
