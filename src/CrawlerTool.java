import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CrawlerTool {
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

    public static void crawlLocation() {

        String fileOutputPath = "TourismLocation.json";
        PrintWriter writer = createAppendFileWriter(fileOutputPath);
        List<TourismLocation> locations = new ArrayList<TourismLocation>();
        String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
        String name;
        String location;
        String province;
        String recYear;
        String category;
        Element table;
        Document doc = new Document("UTF-8");
        try {
            doc = Jsoup.connect(url).get();
        
            for(Element h3 : doc.selectFirst("#mw-content-text .mw-parser-output").select("h3")){
                province = h3.select(".mw-headline").text();
                if(province.equals("Bắc Ninh"))
                    table = h3.nextElementSibling().nextElementSibling();
                else
                    table = h3.nextElementSibling();
                table = table.selectFirst(".wikitable > tbody");
                if(table != null)
                for(Element tr : table.select("tr")) {
                    name = tr.select("td:nth-child(1)").text();
                    location = tr.select("td:nth-child(2)").text() + ", " + province;
                    category = tr.select("td:nth-child(3)").text();
                    recYear = tr.select("td:nth-child(4)").text();
                    if(!name.equals("")) {
                        TourismLocation obj = new TourismLocation(name, location, recYear, category);
                        locations.add(obj);
                    }
                }
            } 
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(locations);
            writer.print(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void crawVietNamKings() {
        List<HistoricalFigure> figures = new ArrayList<HistoricalFigure>();
        String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam#";
        String outputFileName = "VietNamKings.json";
        PrintWriter writer = createAppendFileWriter(outputFileName);
        Document doc = new Document("UTF-8");
        try {
            doc = Jsoup.connect(url).get();
        } catch(IOException e) {
            e.printStackTrace();
        }
        String shortDesciption;
        String name;
        String otherName;
        String dynasty;
        String familyMember;
        Element table;
        String assumeTime;

        for(Element h3 : doc.select("#mw-content-text > div.mw-parser-output > h3")) {
            dynasty = h3.select(".mw-headline").text();
            table = h3.nextElementSibling();
            while(table.selectFirst("tbody") == null)
                table = table.nextElementSibling();
            for(Element tr : table.selectFirst("tbody").select("tr")) {
                if(!tr.attr("style").equals("background:#bdbbd7; height:18px;")) {
                    shortDesciption = "King of Viet Nam";
                    name = tr.select("td:nth-child(2)").text();
                    otherName = tr.select("td:nth-child(6)").text();
                    familyMember = tr.select("td:nth-child(7)").text();
                    assumeTime = "(" + tr.select("td:nth-child(8)").text() + " - "
                                + tr.select("td:nth-child(10)").text() + ")";
                    shortDesciption += assumeTime;
                    figures.add(new HistoricalFigure(name, otherName, "unknow", familyMember, dynasty, shortDesciption));
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(figures);
        writer.print(json);
        writer.close();
    }
    public static void main(String[] args) {
        crawVietNamKings();
    }
}
// https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam#
//

//#mw-content-text > div.mw-parser-output > table:nth-child(17)
//#mw-content-text > div.mw-parser-output > table:nth-child(19)
//#mw-content-text > div.mw-parser-output > table:nth-child(21)
//#mw-content-text > div.mw-parser-output > table:nth-child(25)
//#mw-content-text > div.mw-parser-output > table:nth-child(28)
//#mw-content-text > div.mw-parser-output > table:nth-child(31)
//#mw-content-text > div.mw-parser-output > table:nth-child(34)
//#mw-content-text > div.mw-parser-output > table:nth-child(37)
//#mw-content-text > div.mw-parser-output > table:nth-child(40)
//#mw-content-text > div.mw-parser-output > table:nth-child(42)
//#mw-content-text > div.mw-parser-output > table.table.toccolours > tbody > tr:nth-child(2) > td > table