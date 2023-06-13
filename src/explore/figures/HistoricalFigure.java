package explore.figures;

import java.util.List;

public class HistoricalFigure {
    
    protected String name;
    protected List<String> otherName;
    private String lifeTime;
    private String category;
    
    // Getters
    public String getName() {
        return name;
    }
    public List<String> getOtherName() {
        return otherName;
    }
    public String getLifeTime() {
        return lifeTime;
    }
    public String getCategory() {
        return category;
    }

    // Contructors
    public HistoricalFigure(String name, List<String> otherName, String lifeTime, String category) {
        this.name = name;
        this.otherName = otherName;
        this.lifeTime = lifeTime;
        this.category = category;
    }
}
