package ru.job4j.iostrem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of argument");
        }
        validate(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static void validate(String[] programArg) {
        Path p = Paths.get(programArg[0]);
        if (!(Files.exists(p) && Files.isDirectory(p))) {
            throw new IllegalArgumentException("Invalid directory path.");
        }
        if (!programArg[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong format file extension.");
        }
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
