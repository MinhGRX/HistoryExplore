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

	public static void main(String[] args) throws IOException{
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        String sinh = null;
        String mat = null;
        int count = 0;
        int count1 = 0;
        String[] trieuDai = new String[25];
        final Gson gson = new Gson();
        final String url = "https://vi.wikipedia.org/wiki/Vua_Việt_Nam";
        try {
            final Document doc = Jsoup.connect(url).get();
            System.out.println(doc.title());
            doc.select("sup").remove();
            Elements rows = doc.select("tr[style*=\"height:50px;\"]");
            Elements trieuDais = doc.getElementsByClass("vector-toc-text");
            kings[] king = new kings[rows.size()];
            for (Element row : rows) {
                Element link = row.select("tr td:nth-child(2)").first();
                a = link.text().replaceAll("\\[+[0-9a-zA-Z]+\\]", "");
                Element hrefs = link.select("a[href]").first();
                String hreff = "https://vi.wikipedia.org" + hrefs.attr("href");
                try {
                    Document document = Jsoup.connect(URLDecoder.decode(hreff, "UTF-8")).get();
                        Element box = document.getElementsByClass("infobox").first();
                        if (box != null) {
                            Elements trs = box.getElementsByTag("tr");
                            for (Element tr : trs) {
                                mat = tr.text();
                                if (mat.startsWith("Sinh")) {
                                    sinh = tr.text().replaceAll("\\[+[0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+\\]", "");
                                    sinh = sinh.replaceAll("\\bSinh ?\\b", "");
                                }
                                if (mat.startsWith("Mất")) {
                                    mat = tr.text().replaceAll("\\[+[0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+\\]", "");
                                    mat = mat.replaceAll("\\bMất ?\\b", "");
                                    break;
                                }
                                else mat = "?";
                            }
                        }

                } catch (HttpStatusException e) {
                    // handle the HTTP error status code here
                    int statusCode = e.getStatusCode();
                    if (statusCode == 400 || statusCode == 404) {
                        sinh = "?";
                        mat = "?";
                        System.out.println("Error: " + e.getMessage());
                    } else {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    // handle any other IO exceptions here
                    e.printStackTrace();
                }
                b = row.select("tr td:nth-child(5)").text().replaceAll("\\[+[0-9a-zA-Z]+\\]", "");
                c = row.select("tr td:nth-child(n+8)").text().replaceAll("\\[+[0-9a-zA-Z]+\\]", "");
                d = row.select("tr td:nth-child(7)").text().replaceAll("\\[+[0-9a-zA-Z]+\\]", "");
                king[count] = new kings(count + 1, a, sinh, mat,null, b, c, d);
                sinh = "?";
                mat = "?";
                count++;

            }
            Elements div = doc.getElementsByClass("vector-toc-text");
            for(Element thoiKy : div){
                Element thuTu = thoiKy.getElementsByClass("vector-toc-numb").first();
                if(thuTu != null && !NumberUtils.isDigits(thuTu.text())&& Double.parseDouble(thuTu.text())>1 && Double.parseDouble(thuTu.text()) < 11 ){
                    trieuDai[count1] = thoiKy.text().replaceAll("\\((.*?)\\)|[0-9.]|hoặc","");
                    count1++;
                }
            }
            count1 = 0;
            int count2 = 0;
            Elements tables = doc.getElementsByTag("table");
            for (Element table : tables) {
                Elements rowss = table.select("tr[style*=\"height:50px;\"]");
                if (rowss.size() != 0) {
                    for (Element row : rowss) {
                        king[count1].setTrieuDai(trieuDai[count2]);
                        count1++;
                    }
                    count2++;
                }
            }
            for (int i = 1; i <= count; i++) {
                FileWriter writer = new FileWriter("vua\\" + i + ".json");
                String json = gson.toJson(king[i - 1]);
                writer.write(json);
                writer.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
