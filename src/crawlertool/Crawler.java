package crawlertool;

import java.util.List;

import explore.dynasty.Dynasty;
import explore.event.Festival;
import explore.event.VietNamWar;
import explore.figures.HistoricalFigure;
import explore.location.TourismLocation;

public class Crawler {
    private DynastyCrawler dynastyCrawler = new DynastyCrawler();
    private EventCrawler eventCrawler = new EventCrawler();
    private FiguresCrawler figuresCrawler = new FiguresCrawler();
    private TouristLocationCrawler touristLocationCrawler = new TouristLocationCrawler();
    
    private List<Dynasty> dynasties; 
    private List<Festival> festivals;
    private List<VietNamWar> wars;
    private List<HistoricalFigure> emperors;
    private List<TourismLocation> locations;

    public Crawler() {
        this.dynasties = dynastyCrawler.crawlVietNamDynasty();
        this.festivals = eventCrawler.crawlFestival();
        this.wars = eventCrawler.crawlWar();
        this.emperors = figuresCrawler.crawlVietNamKings();
        this.locations = touristLocationCrawler.crawlLocation();
    }

    public List<Dynasty> getDynasties() {
        return dynasties;
    }

    public List<Festival> getFestivals() {
        return festivals;
    }

    public List<VietNamWar> getWars() {
        return wars;
    }

    public List<HistoricalFigure> getEmperors() {
        return emperors;
    }

    public List<TourismLocation> getLocations() {
        return locations;
    }
}
