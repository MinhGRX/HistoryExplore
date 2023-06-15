package crawlertool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import explore.location.TourismLocation;

public class TouristLocationCrawler {

    public List<TourismLocation> crawlLocation() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
// https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam#
