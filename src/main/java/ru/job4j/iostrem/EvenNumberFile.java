package ru.job4j.iostrem;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    System.out.println("Число " + number + " является четным");
                } else {
                    System.out.println("Число " + number + " является нечетным");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

