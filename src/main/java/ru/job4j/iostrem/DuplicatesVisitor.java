package ru.job4j.iostrem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapFiles = new HashMap<>();
    private Path begin;

    public DuplicatesVisitor(Path begin) {
        this.begin = begin;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty  rsl = new FileProperty(file.toFile().length(), file.toFile().getName());
        mapFiles.putIfAbsent(rsl, new ArrayList<>());
        mapFiles.get(rsl).add(file);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        List<Path> list;
        if (begin.equals(dir)) {
            for (FileProperty key : mapFiles.keySet()) {
                list = mapFiles.get(key);
                if (list.size() > 1) {
                    System.out.println(key.getName() + ' ' + key.getSize());
                    for (Path p : list) {
                        System.out.println(p);
                    }
                }
            }
        }
        return super.postVisitDirectory(dir, exc);
    }
}
