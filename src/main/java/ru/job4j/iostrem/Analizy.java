package ru.job4j.iostrem;
import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        String begin = "";
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] lines = line.split(" ");
                boolean noWork = "400".equals(lines[0]) || "500".equals(lines[0]);
                boolean work = "200".equals(lines[0]) || "300".equals(lines[0]);
                if (noWork && "".equals(begin)) {
                    begin = lines[1];
                }
                if (work && !"".equals(begin)) {
                    out.println(begin + ";" + lines[1]);
                    begin = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");

            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        unavailable("unavailable.csv", "target.csv");
    }

}
