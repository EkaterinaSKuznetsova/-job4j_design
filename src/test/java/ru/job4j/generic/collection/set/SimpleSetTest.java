package ru.job4j.generic.collection.set;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenFalseContains() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertFalse(set.contains(0));
    }

    @Test
    public void whenTrueContains() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
    }

    @Test
    public void whenNoAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertFalse(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(3));
        assertTrue(set.contains(3));
    }

    @Test
    public void whenAddNullInNoEmptySet() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }
}