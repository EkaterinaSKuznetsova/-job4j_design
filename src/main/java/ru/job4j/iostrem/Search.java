package ru.job4j.iostrem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        variable(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];

        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static void variable(String[] programArg) {
        if (programArg.length == 0) {
            throw new IllegalArgumentException("Begin folder is null. Usage  BEGIN_FOLDER.");
        }
        if (programArg.length == 1) {
            throw new IllegalArgumentException("Search extension is null. Usage  SEARCH_EXTENSION.");
        }
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
