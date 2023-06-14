package crawlertool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import explore.dynasty.Dynasty;

public class DynastyCrawler {

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

    private static String getTimeLine(String name, List<String> details) {
        String timeline = "";
        for (String detail : details) {
            int match = detail.indexOf(name);
            if(match != -1) {
                timeline = detail.substring(match);
                timeline = timeline.substring(timeline.indexOf("(")+1, timeline.indexOf(")"));
            }
        }
        return timeline;
    }
    
    private static void crawlVietNamDynasty() {
        String fileOutput = "lib/ObjectData/Dynasty.json";
        PrintWriter writer = createAppendFileWriter(fileOutput);
        List<Dynasty> dynasties = new ArrayList<Dynasty>();
        String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
        Pattern pattern = Pattern.compile("\\[\\w+\\]");
		Matcher matcher;

        String name;
        String founder;
        String capital;
        String time;

        Document doc = new Document("UTF-8");
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element summaryTable = doc.selectFirst("#mw-content-text > div.mw-parser-output > div.navbox > table > tbody");
        List<String> dynastyStr = new ArrayList<String>();
        for (Element tr : summaryTable.select("tr")) {
            dynastyStr.add(tr.selectFirst("th").text());
        }

        for(Element h2 : doc.select("#mw-content-text > div.mw-parser-output h3")) {
            dynastyStr.add(h2.text());
        }
            
        Element table = doc.selectFirst("#mw-content-text > div.mw-parser-output > table:nth-child(91) > tbody"); 

        for(Element tr : table.select("tr")) {
            if(!tr.attr("style").equals("text-align:center;")) {
                name = tr.selectFirst("td:nth-child(1)").text();
                matcher = pattern.matcher(name);
                name = matcher.replaceAll("");

                time = getTimeLine(name, dynastyStr);

                founder = tr.selectFirst("td:nth-child(2)").text();
                matcher = pattern.matcher(founder);
                founder = matcher.replaceAll("");

                capital = tr.selectFirst("td:nth-child(4)").text();
                matcher = pattern.matcher(capital);
                capital = matcher.replaceAll("");

                dynasties.add(new Dynasty(name, founder, capital, time));
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dynasties);
        writer.print(json);
        writer.close();
    }

    public static void main(String[] args){
        crawlVietNamDynasty();
    }

    
}
