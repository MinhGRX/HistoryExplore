package crawlertool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

  	private static void crawlVietNamKings() {
		List<HistoricalFigure> figures = new ArrayList<HistoricalFigure>();
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam#";
		String outputFileName = "VietNamKings.json";
		PrintWriter writer = createAppendFileWriter(outputFileName);
		Document doc = new Document("UTF-8");
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String shortDesciption;
		String name;
		String otherName;
		String dynasty;
		String familyMember;
		Element table;
		String assumeTime;
		Pattern pattern = Pattern.compile("\\[\\w+\\]");
		Matcher matcher;

		for (Element h3 : doc.select("#mw-content-text > div.mw-parser-output > h3")) {
			dynasty = h3.select(".mw-headline").text();
			table = h3.nextElementSibling();
			while (table.selectFirst("tbody") == null) 
				table = table.nextElementSibling();
			for (Element tr : table.selectFirst("tbody").select("tr")) {
				if (!tr.attr("style").equals("background:#bdbbd7; height:18px;")) {
					shortDesciption = "King of Viet Nam";
					name = tr.select("td:nth-child(2)").text();
					otherName = tr.select("td:nth-child(6)").text();
					familyMember = tr.select("td:nth-child(7)").text();
					assumeTime = "(" + tr.select("td:nth-child(8)").text() +
								" - " + tr.select("td:nth-child(10)").text() + ")";
					shortDesciption += assumeTime;

					// Standart name
					matcher = pattern.matcher(name);
					name = matcher.replaceAll("");
					//Standart otherName
					matcher = pattern.matcher(otherName);
					otherName = matcher.replaceAll("");
					//Standart familyMember
					matcher = pattern.matcher(familyMember);
					familyMember = matcher.replaceAll("");
					//Standart dynasty
					matcher = pattern.matcher(dynasty);
					dynasty = matcher.replaceAll("");
					//Standart shortDescription
					matcher = pattern.matcher(shortDesciption);
					shortDesciption = matcher.replaceAll("");

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
		crawlVietNamKings();
	}
}
