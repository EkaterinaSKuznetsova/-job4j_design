package ru.job4j.iostrem;
import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        boolean noWorkServer = false;
        String begin = "";
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            try (BufferedReader in = new BufferedReader(new FileReader(source))) {
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    String[] lines = line.split(" ");
                    if ((lines[0].equals("400") || lines[0].equals("500")) && !noWorkServer) {
                        noWorkServer = true;
                        if (begin.isEmpty()) {
                           begin = lines[1];
                        }
                    }
                    if ((lines[0].equals("200") || lines[0].equals("300")) && noWorkServer) {
                        noWorkServer = false;
                        if (!begin.isEmpty()) {
                            out.println(begin + ";" + lines[1]);
                            begin = "";
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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
