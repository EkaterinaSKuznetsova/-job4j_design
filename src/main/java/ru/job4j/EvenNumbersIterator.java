package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
       if (index < data.length) {
          for (int i = index; i < data.length; i++) {
               if (data[i] % 2 == 0) {
                   return true;
               }
           }
       }
       return  false;
    }

    @Override
    public Integer next() {
       Integer result = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = index; i < data.length; i++) {
            index++;
            if (data[i] % 2 == 0) {
               result = data[i];
               break;
            }
        }
        return result;
    }

}
