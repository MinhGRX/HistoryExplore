package crawlertool;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import explore.figures.Emperor;
import explore.figures.HistoricalFigure;

public class FiguresCrawler {
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
    private static int getDynasty(List<String> names, List<String>kings) {
        int i = 0;
        for(String king: kings)
        {
            for(String name: names)
                if(king.toLowerCase().contains(name.toLowerCase()))
                    return i;
            i++;
        }
        return 0;
    }

    private static String getLivingTime(Document doc, String match) {
        String time = "?";
        Pattern pattern = Pattern.compile("\\[+[0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+\\]");
        if(doc != null) {
            Element box = doc.selectFirst("#mw-content-text > div.mw-parser-output > table.infobox");
            if (box != null) {
                for (Element tr : box.select("tbody > tr")) {
                    if(tr.text().toLowerCase().contains(match)) {
                        time = pattern.matcher(tr.text()).replaceAll("");
                        break;
                    }
                }
            }
        }
        return time;
    }

  	private static void crawlVietNamKings() {
		List<HistoricalFigure> figures = new ArrayList<HistoricalFigure>();
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam#";
		String outputFileName = "lib/ObjectData/VietNamKings.json";
		PrintWriter writer = createAppendFileWriter(outputFileName);
		Document doc = new Document("UTF-8");
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // List emperors of dynasties
        List<String> dynasties = new ArrayList<String>();
        List<String> kings = new ArrayList<String>();
        for(Element tr : doc.select("#mw-content-text > div.mw-parser-output > div.navbox > table > tbody > tr")) {
                dynasties.add(tr.selectFirst("th").text());
                kings.add(tr.select("td > div").text());
        }
        dynasties.set(0, "");
        String shortDesciption;
		String name;
		String dynasty;
		String assumeTime;
        String birth;
        String death;
        List<String> names;

        // Regex to remove unnecessary charracters
		Pattern pattern = Pattern.compile("\\[\\w+\\]");

        shortDesciption = "Vua Việt Nam";
		for (Element table : doc.select("#mw-content-text > div.mw-parser-output > table")) {
            if(table.attr("cellpadding").equals("0"))
                for (Element tr : table.selectFirst("tbody").select("tr")) {
                    if (!tr.attr("style").equals("background:#bdbbd7; height:18px;")) {
                        names = new ArrayList<String>();
                        name = tr.select("td:nth-child(2)").text();
                        name = pattern.matcher(name).replaceAll("");
                        if(!name.isEmpty()) {
                            assumeTime = tr.select("td:nth-child(8)").text() +
                                        " - " + tr.select("td:nth-child(10)").text();
                            assumeTime = pattern.matcher(assumeTime).replaceAll("");
                            Document charDoc = new Document("UTF-8");
                            
                            try {
                                if(tr.selectFirst("td:nth-child(2) i") == null)
                                    charDoc = Jsoup.connect("https://vi.wikipedia.org" + URLDecoder.decode(tr.selectFirst("td:nth-child(2) a").attr("href"), "UTF-8")).get();
                            } catch (IOException exception) {
                                charDoc = null;
                            }
                            
                            birth = getLivingTime(charDoc, "sinh").replaceAll("\\bSinh ?\\b", "");
                            death = getLivingTime(charDoc, "mất").replaceAll("\\bMất ?\\b", "");
                            names.add(name);
                            for(String x : tr.select("td:nth-child(4)").text().split(", ")) {
                                x = pattern.matcher(x).replaceAll("");
                                if(!names.contains(x) && !x.contains("không"))
                                    names.add(x);
                            }
                            for(String x : tr.select("td:nth-child(6)").text().split(", ")) {
                                x = pattern.matcher(x).replaceAll("");
                                if(!names.contains(x) && !x.contains("không"))
                                    names.add(x);
                            }

                            dynasty = dynasties.get(getDynasty(names, kings));
                            dynasty = pattern.matcher(dynasty).replaceAll("");
                            figures.add(new Emperor(name, names, shortDesciption, birth, death, dynasty, assumeTime));
                            System.out.println(name);
                        }
                    }
                }
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(figures);
		writer.print(json);
		writer.close();
	}

	public static void main(String[] args) {
        crawlVietNamKings();
    }
}
