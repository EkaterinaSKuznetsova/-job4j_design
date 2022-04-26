package ru.job4j.generic.collection;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenremoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeIf(input, value -> value < 2);
        assertThat(input, is(Arrays.asList(2, 3)));
    }

    @Test
    public void whenreplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.replaceIf(input, value -> value < 2, 2);
        assertThat(input, is(Arrays.asList(2, 2, 2, 3)));
    }

    @Test
    public void whenremoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 6));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(2, 3, 4, 5, 7, 8)));
    }
}