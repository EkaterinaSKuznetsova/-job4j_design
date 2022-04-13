package ru.job4j.generic.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> after;
    private int size;
    private int modCount;
    private int expectedModCount;

    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value, null);
        if (first == null) {
            first = node;
        } else {
            Node<E> next = first;
            while (next.next != null) {
                next = next.next;
            }
            next.next = node;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> next = first;
        for (int i = 0; i < index; i++) {
            next = next.next;
        }
        return next.value;
    }

    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        after = first;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return after != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> next = after;
                after = after.next;
                return next.value;
            }
        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
