package org.example;

import org.example.math.FuncSystem;
import org.example.utils.CsvWriter;

public class Main {
    public static void main(String[] args) {
        CsvWriter.writeToCSV("output.csv", -10, 10, 0.5, ";", FuncSystem::calculate);

        System.out.println("CSV файл успешно создан!");
    }
}