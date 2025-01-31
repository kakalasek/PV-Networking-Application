package com.Utils.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides some handy methods for communication with files
 */
public class FileHandler {

    /**
     * Writes a list of Strings into a csv file
     * @param filepath The file path of your csv file
     * @param rows The data you want to write. Each Array of Strings represents a row
     * @throws IOException Gets thrown if any error occurred while writing the data
     */
    public static void writeCsv(String filepath, List<String[]> rows) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))){
            for(String[] row : rows){
                StringBuilder rowString = new StringBuilder();
                for(String attribute : row){
                    rowString.append(attribute).append(",");
                }
                rowString.replace(rowString.length()-1,rowString.length(), "");
                bufferedWriter.write(rowString + "\n");
            }
        }
    }

    /**
     * Appends a row into a csv file
     * @param filepath The file path of your csv file
     * @param row The row you want to append. Each element inside the Array represents a value in the row
     * @throws IOException Gets thrown if any error occurred while appending the data
     */
    public static void appendCsvRow(String filepath, String[] row) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, true))){
                StringBuilder rowString = new StringBuilder();
                for(String attribute : row){
                    rowString.append(attribute).append(",");
                }
                rowString.replace(rowString.length()-1,rowString.length(), "");
                bufferedWriter.write(rowString + "\n");
        }
    }

    /**
     * Reads a csv file and stores its contents inside a List of String Arrays
     * @param filepath The file path of your csv file
     * @return List of String Arrays. Each Array represents a row inside the file
     * @throws IOException Gets thrown if any error occurred while reading the data
     */
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

    /**
     * Clears a file by writing an empty string into it
     * @param filepath The file path of your file
     * @throws IOException Gets thrown if any error occurred while clearing the file
     */
    public static void clearFile(String filepath) throws IOException{
        try(FileWriter fileWriter = new FileWriter(filepath)){
            fileWriter.write("");
        }
    }
}
