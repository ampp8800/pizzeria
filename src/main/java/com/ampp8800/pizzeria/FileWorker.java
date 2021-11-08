package com.ampp8800.pizzeria;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWorker {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");

    static void writeFile(String output) {
        try {
            String fileName = dateFormat.format(new Date()) + ".txt";
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(output);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
