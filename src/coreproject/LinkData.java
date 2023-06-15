package coreproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import explore.figures.Emperor;

public class LinkData {
    
    private static List<Emperor> jsonToEmperors(String filePath) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
                json.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Reading failure");
        }
        String data = json.toString();
        String object = "";
        String name = "";
        String dynasty = "";
        String reignTime = "";
        String category = "";
        String birth = "";
        String death = "";
        String otherNamesStr;
        List<String> otherNames;
        List<Emperor> emperors = new ArrayList<Emperor>();
        while(json.indexOf("{") >= 0) { 
            object = data.substring(json.indexOf("{"), json.indexOf("},"));
            dynasty = object.substring(object.indexOf(": \"") + 3, object.indexOf("\","));
            object = object.substring(object.indexOf("\",\n") + 4);
            reignTime = object.substring(object.indexOf(": \"") + 3, object.indexOf("\","));
            object = object.substring(object.indexOf("\",\n") + 4);
            name = object.substring(object.indexOf(": \"") + 3, object.indexOf("\","));
            object = object.substring(object.indexOf("\",\n") + 4);
            otherNamesStr = object.substring(object.indexOf("["), object.indexOf("],"));
            otherNames = new ArrayList<String>();
            for(String otherName : otherNamesStr.split(",\n")) {
                otherName = otherName.substring(otherName.indexOf("\"") + 1, otherName.lastIndexOf("\""));
                otherNames.add(otherName);
            }
            object = object.substring(object.indexOf("],\n") + 4);
            category = object.substring(object.indexOf(": \"") + 3, object.indexOf("\","));
            object = object.substring(object.indexOf("\",\n") + 4);
            birth = object.substring(object.indexOf(": \"") + 3, object.indexOf("\","));
            object = object.substring(object.indexOf("\",\n") + 4);
            death = object.substring(object.indexOf(": \"") + 3, object.lastIndexOf("\""));
            emperors.add(new Emperor(name, otherNames, category, birth, death, dynasty, reignTime));
            data = data.substring(data.indexOf("},\n") + 4);
        }
        return emperors;
    }
    public static void main(String[] args) {
        List<Emperor> emperors = jsonToEmperors("lib/ObjectData/VietNamKings.json");
        for(Emperor obj : emperors)
            System.out.println(obj.toString());
    }
}
