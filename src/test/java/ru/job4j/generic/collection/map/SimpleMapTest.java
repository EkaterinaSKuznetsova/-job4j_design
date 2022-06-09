package ru.job4j.generic.collection.map;

import org.junit.Assert;
import org.junit.Test;

import ru.job4j.generic.collection.map.Map;
import ru.job4j.generic.collection.map.SimpleMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutNonNull() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
    }

    @Test
    public void whenPutNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(812, "Санкт - Петербург");
        map.put(499, "Москва");
        assertFalse(map.put(812, "Санкт - Петербург"));
    }

    @Test
    public void whenAddNullKey() {
        Map<Integer, String> map = new SimpleMap<>();
        assertFalse(map.put(null, "Санкт - Петербург"));
    }

    @Test
    public void whenAddNullValue() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(0, null));
    }

    @Test
    public void whenGetNonNull() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
        Assert.assertEquals(map.get(812), "Санкт - Петербург");

    }

    @Test
    public void whenGetNull() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
        assertTrue(map.put(499, "Москва"));
        assertNull(map.get(495));
    }

    @Test
    public void whenRemoveTrue() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
        assertTrue(map.put(499, "Москва"));
        assertTrue(map.put(495, "Москва"));
        assertTrue(map.remove(499));
        assertNull(map.get(499));
        Assert.assertEquals(map.get(812), "Санкт - Петербург");
        Assert.assertEquals(map.get(495), "Москва");
    }

    @Test
    public void whenRemoveFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
        assertTrue(map.put(499, "Москва"));
        assertTrue(map.put(495, "Москва"));
        assertFalse(map.remove(496));
        Assert.assertEquals(map.get(812), "Санкт - Петербург");
        Assert.assertEquals(map.get(495), "Москва");
        Assert.assertEquals(map.get(499), "Москва");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        assertTrue(map.put(812, "Санкт - Петербург"));
        iterator.next();
    }

    @Test
    public void whenCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(812, "Санкт - Петербург"));
        assertTrue(map.put(499, "Москва"));
        assertTrue(map.put(495, "Москва"));
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        System.out.println(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        System.out.println(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        System.out.println(iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }
}
