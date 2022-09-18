package ru.job4j.iostrem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Integer> mapFilesQuality = new HashMap<>();
    private Map<Path, FileProperty> mapFilePath = new HashMap<>();
    private Path begin;

    public DuplicatesVisitor(Path begin) {
        this.begin = begin;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty  rsl = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (mapFilesQuality.containsKey(rsl)) {
            mapFilesQuality.compute(rsl, (k, v) -> v = v + 1);
        } else {
            mapFilesQuality.put(rsl, 1);
        }
        mapFilePath.put(file.toAbsolutePath(),rsl);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (begin.equals(dir)) {
            System.out.println("end program");
            for (FileProperty fp : mapFilesQuality.keySet()) {
                if (mapFilesQuality.get(fp) > 1) {
                    System.out.println(fp.getName() + ' ' + fp.getSize());
                    for (Path p : mapFilePath.keySet()) {
                        if (fp.equals(mapFilePath.get(p))) {
                            System.out.println(p);
                        }
                    }
                }
            }
        }
        return super.postVisitDirectory(dir, exc);
    }

}
