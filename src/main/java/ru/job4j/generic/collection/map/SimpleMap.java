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
    private int quality;
    private int index;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean res = false;
        if (key != null) {
            int i = indexFor(hash(key.hashCode()));
            if (i >= table.length) {
                expand();
            }
            if (table[i] == null)  {
                table[i] = new MapEntry<>(key, value);
                count++;
                res = true;
            }
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        capacity = capacity * 2;
        table = Arrays.copyOf(table, capacity);
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        V res = (i < table.length && table[i] != null) ? table[i].value : null;
        return res;
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        int i = indexFor(hash(key.hashCode()));
        if (i < table.length && table[i] != null && table[i].key.equals(key)) {
            table[i] = null;
            count = count - 1;
            res = true;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        modCount = count;
        quality = 0;
        index = 0;
        return new Iterator<K>() {

            @Override
            public boolean hasNext() {
                if (modCount != count) {
                    throw new ConcurrentModificationException();
                }

                return quality < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[index] == null && index < table.length) {
                    index++;
                }
                quality++;
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
