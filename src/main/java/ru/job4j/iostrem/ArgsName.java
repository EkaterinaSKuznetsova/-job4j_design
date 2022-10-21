package ru.job4j.iostrem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not found");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] res;
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Incorrect first argument in string: " + arg);
            }
            res = arg.split("=", 2);
            if (res.length != 2 || res[0].isEmpty() || res[1].isEmpty()) {
                throw new IllegalArgumentException("Incorrect argument in string: " + arg);
            }
            values.put(res[0].substring(1), res[1]);
        }
    }


    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Incorrect number of argument");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

