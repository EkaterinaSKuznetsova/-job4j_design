package ru.job4j.iostrem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizyTest {
     @Test
     void testOne(@TempDir Path tempDir) throws IOException {
            File source = tempDir.resolve("unavailable.csv").toFile();
            try (PrintWriter out = new PrintWriter(source)) {
                out.println("200 10:56:01");
                out.println("500 10:57:01");
                out.println("400 10:58:01");
                out.println("200 10:59:01");
                out.println("500 11:01:02");
                out.println("200 11:02:02");
            }
            File target  = tempDir.resolve("target.csv").toFile();
            Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
            StringBuilder rsl = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new FileReader(target))) {
                in.lines().forEach(rsl::append);
            }
            assertThat("10:57:01;10:59:01" +
                    "11:01:02;11:02:02").isEqualTo(rsl.toString());
     }

    @Test
    void testTwo(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("unavailable.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;11:02:02").isEqualTo(rsl.toString());
    }
    @Test
    void testNull(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("unavailable.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("300 10:57:01");
            out.println("200 10:58:01");
            out.println("300 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("").isEqualTo(rsl.toString());
    }
}
