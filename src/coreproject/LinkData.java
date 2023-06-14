package coreproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class LinkData {
    private static PrintWriter createAppendFileWriter(String filePath) {
        try {
            File outputFile = new File(filePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(outputFile, true);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)));
            return writer;
            }
            catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    
    private static String[] readJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            StringBuilder json = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                json.append(line);
                json.append("\n");
            }
            String jsons =  json.substring(json.indexOf("[")+1, json.lastIndexOf("]"));
            return jsons.split("},\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Reading failure");
        }
        return null;
    }
    public static void main(String[] args) {
        String filePath = "lib/ObjectData/VietNamKings.json";
        PrintWriter writer = createAppendFileWriter("test.txt");
        String kings = readJsonFile(filePath)[0];
        writer.print(kings);   
        writer.close();
    }
}
