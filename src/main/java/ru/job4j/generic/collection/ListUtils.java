package ru.job4j.generic.collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.List;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
       ListIterator<T> iterator = list.listIterator(index);
       iterator.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iteratorList = list.listIterator();
        while (iteratorList.hasNext()) {
            if (elements.contains(iteratorList.next())) {
                iteratorList.remove();
            }
        }
    }
}


