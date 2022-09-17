package ru.job4j.iostrem;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_elementary");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.print(subfile.getAbsoluteFile());
            System.out.println(String.format("   length : %s", subfile.length()));

        }
    }
}
