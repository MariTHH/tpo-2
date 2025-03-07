package org.example.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.DoubleUnaryOperator;

public class CsvWriter {
    public static void writeToCSV(String filename, double start, double end, double step, String delimiter, DoubleUnaryOperator func) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X" + delimiter + "Function Result\n");
            for (double x = start; x <= end; x += step) {
                writer.write(x + delimiter + func.applyAsDouble(x) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
