package ru.job4j.generic.collection.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private int iteratorCount;
    private int index;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean res = false;

        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        if (key != null) {
            int i = indexFor(hash(key.hashCode()));
            if (table[i] == null) {
                table[i] = new MapEntry<>(key, value);
                count++;
                res = true;
                modCount++;
            }
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return  hash % (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
        modCount++;

    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        V res = (table[i] != null && table[i].key.hashCode() == key.hashCode() && table[i].key.equals(key)) ? table[i].value : null;
        return res;
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null && table[i].key.hashCode() == key.hashCode() && key.equals(table[i].key)) {
            table[i] = null;
            count = count - 1;
            res = true;
            modCount++;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        iteratorCount = modCount;
        index = 0;
        return new Iterator<K>() {

            @Override
            public boolean hasNext() {
                if (modCount != iteratorCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
