package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean evenNumber(int i) {
        return data[i] % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        if (index < data.length) {
            for (int i = index; i < data.length; i++) {
                if (evenNumber(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = index; i < data.length; i++) {
            index++;
            if (evenNumber(i)) {
                break;
            }
        }
        return data[index - 1];
    }

}
