package explore.figures;

import java.util.List;

public class Emperor extends HistoricalFigure {
    private String dynasty;
    private String reignTime;
    
    public String getDynasty() {
        return dynasty;
    }
    public String getReignTime() {
        return reignTime;
    }

    public Emperor(String name, List<String> otherName, String lifeTime, String category, String dynasty, String reignTime) {
        super(name, otherName, lifeTime, category);
        this.dynasty = dynasty;
        this.reignTime = reignTime;
    }
 
}
