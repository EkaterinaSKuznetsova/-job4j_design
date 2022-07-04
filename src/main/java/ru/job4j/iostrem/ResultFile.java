package ru.job4j.iostrem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Таблица умножения:".getBytes(StandardCharsets.UTF_8));
            out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            for (int i = 1; i <= 9; i++) {
                out.write("на ".getBytes(StandardCharsets.UTF_8));
                out.write(Integer.toString(i).getBytes(StandardCharsets.UTF_8));
                out.write(":".getBytes(StandardCharsets.UTF_8));
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                for (int j = 1; j <= 9; j++) {
                    out.write(Integer.toString(i).getBytes(StandardCharsets.UTF_8));
                    out.write(" * ".getBytes(StandardCharsets.UTF_8));
                    out.write(Integer.toString(j).getBytes(StandardCharsets.UTF_8));
                    out.write(" = ".getBytes(StandardCharsets.UTF_8));
                    out.write(Integer.toString(i * j).getBytes(StandardCharsets.UTF_8));
                    out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                }
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

