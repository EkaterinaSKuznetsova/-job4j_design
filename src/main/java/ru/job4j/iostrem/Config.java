package ru.job4j.iostrem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalAccessException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }
                int index = line.indexOf('=');
                if (index == -1 || index == line.length() - 1 || index == 0) {
                    System.out.println("неправильный формат строки: " + line);
                    throw new IllegalAccessException();
                }
                values.put(line.substring(0, index), line.substring(index + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.username"));
    }
}
