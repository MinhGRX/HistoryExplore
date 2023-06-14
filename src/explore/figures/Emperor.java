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
    public Emperor(String name, List<String> otherName, String category, String birth, String death,
            String dynasty, String reignTime) {
        super(name, otherName, category, birth, death);
        this.dynasty = dynasty;
        this.reignTime = reignTime;
    } 
}
