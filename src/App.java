import java.util.List;

import crawlertool.Crawler;
import crawlertool.DataControler;
import explore.dynasty.Dynasty;
import explore.event.Festival;
import explore.event.VietNamWar;
import explore.figures.HistoricalFigure;
import explore.location.TourismLocation;

public class App {
    public static void main(String[] args) {
        DataControler data = new DataControler();
        Crawler crawler = new Crawler();
        List<Dynasty> dynasties = crawler.getDynasties();
        List<Festival> festivals = crawler.getFestivals();
        List<HistoricalFigure> emperors = crawler.getEmperors();
        List<TourismLocation> locations = crawler.getLocations();
        List<VietNamWar> wars = crawler.getWars();
        
        
        data.dynastyToFile(dynasties, "dynasty.json");
        System.out.println("Dynaties");
        data.festivalToFile(festivals, "festival.json");
        System.out.println("festival");
        data.figuresToFile(emperors, "king.json");
        System.out.println("king");
        data.locationToFile(locations, "location.json");
        System.out.println("location");
        data.warToFile(wars, "war.json");
        System.out.println("war");
    }
}