package com.Utils.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void writeCsv(String filepath, List<String[]> rows) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))){
            for(String[] row : rows){
                StringBuilder rowString = new StringBuilder();
                for(String attribute : row){
                    rowString.append(attribute).append(",");
                }
                bufferedWriter.write(rowString.toString());
            }
        }
    }

    public static void appendCsvRow(String filepath, String[] row) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, true))){
                StringBuilder rowString = new StringBuilder();
                for(String attribute : row){
                    rowString.append(attribute).append(",");
                }
                bufferedWriter.write(rowString.toString());
        }
    }

    public static List<String[]> readCsv(String filepath) throws IOException{
        List<String[]> rows = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                rows.add(line.split(","));
            }
        }

        return rows;
    }
}
