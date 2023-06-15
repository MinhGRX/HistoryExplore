package crawlertool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import explore.dynasty.Dynasty;
import explore.event.Festival;
import explore.event.VietNamWar;
import explore.figures.HistoricalFigure;
import explore.location.TourismLocation;

public class DataControler {
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
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public void locationToFile(List<TourismLocation> locations, String filepath) {
        PrintWriter writer = createAppendFileWriter(filepath);
        String json = gson.toJson(locations);
        writer.print(json);
        writer.close();
        System.out.println("Data exported");
    }

    public void dynastyToFile(List<Dynasty> dynasties, String filepath) {
        PrintWriter writer = createAppendFileWriter(filepath);
        String json = gson.toJson(dynasties);
        writer.print(json);
        writer.close();
        System.out.println("Data exported");
    }

    public void festivalToFile(List<Festival> festivals, String filepath) {
        PrintWriter writer = createAppendFileWriter(filepath);
        String json = gson.toJson(festivals);
        writer.print(json);
        writer.close();
        System.out.println("Data exported");
    }

    public void warToFile(List<VietNamWar> wars, String filepath) {
        PrintWriter writer = createAppendFileWriter(filepath);
        String json = gson.toJson(wars);
        writer.print(json);
        writer.close();
        System.out.println("Data exported");
    }

    public void figuresToFile(List<HistoricalFigure> figures, String filepath) {
        PrintWriter writer = createAppendFileWriter(filepath);
        String json = gson.toJson(figures);
        writer.print(json);
        writer.close();
        System.out.println("Data exported");
    }
}
