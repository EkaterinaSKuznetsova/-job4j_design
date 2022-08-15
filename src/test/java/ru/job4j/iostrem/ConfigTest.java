package ru.job4j.iostrem;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() throws IllegalAccessException {
        String path = "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void badFormat() throws IllegalAccessException {
        String path = "bad_format.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("org.hibernate.dialect.PostgreSQLDialect"), is("hibernate.dialect=trackstudio"));
    }

    @Test
    public void badFormatTwo() throws IllegalAccessException {
        String path = "bad_format_2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("org.hibernate.dialect.PostgreSQLDialect"), is("hibernate.dialect="));
    }

    @Test (expected = IllegalAccessException.class)
    public void errorFormat() throws IllegalAccessException {
        String path = "error_format.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalAccessException.class)
    public void errorFormatTwo() throws IllegalAccessException {
        String path = "error_format2.properties";
        Config config = new Config(path);
        config.load();
    }
}
