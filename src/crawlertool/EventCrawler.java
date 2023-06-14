package crawlertool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import explore.event.*;

public class EventCrawler {
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
    
    private static void crawlWar() {
        String url = "https://vi.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia";
        List<VietNamWar> events = new ArrayList<VietNamWar>();
        String name;
        String time;
        //String location;
        String result;
        String homeForce, enemyForce;

        Document doc = new Document("UTF-8");
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(Element table: doc.select("#mw-content-text > div.mw-parser-output > .wikitable")) {
            for(Element tr : table.selectFirst("tbody").select("tr")) {
                if(tr.selectFirst("td") != null) {
                    name = tr.select("td:nth-child(1)").text();
                    time = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
                    homeForce = tr.select("td:nth-child(2)").text();
                    enemyForce = tr.select("td:nth-child(3)").text();
                    result = tr.select("td:nth-child(4)").text();
                    events.add(new VietNamWar(name, time, "", homeForce, enemyForce, result));  
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(events);
        PrintWriter writer = createAppendFileWriter("lib/ObjectData/VietNamWar.json");
        writer.print(json);
        writer.close();
    }

    private static void crawlFestival() {
        String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
        List<Festival> events = new ArrayList<Festival>();
        String name;
        String time;
        String location;
        String relativeFigure;

        Document doc = new Document("UTF-8");
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Element tr : doc.select("#mw-content-text > div.mw-parser-output > table.prettytable.wikitable > tbody > tr")) {
            if(!tr.attr("bgcolor").equals("#CCCCCC")) {
                time = tr.select("td:nth-child(1)").text();
                location = tr.select("td:nth-child(2)").text();
                name = tr.select("td:nth-child(3)").text();
                relativeFigure = tr.select("td:nth-child(5)").text();
                events.add(new Festival(name, time, location, relativeFigure));
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(events);
        PrintWriter writer = createAppendFileWriter("lib/ObjectData/VietNamFestival.json");
        writer.print(json);
        writer.close();
    }
    public static void main(String[] args) {
        crawlFestival();
        crawlWar();
    }

}
//https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam
//https://vi.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia