package explore.figures;

import java.util.List;

public class HistoricalFigure {
    
    protected String name;
    protected List<String> otherName;
    private String category;
    private String birth;
    private String death;
    
    // Getters
    public String getName() {
        return name;
    }
    public List<String> getOtherName() {
        return otherName;
    }
    public String getCategory() {
        return category;
    }
    public String getBirth() {
        return birth;
    }
    public String getDeath() {
        return death;
    }
    // Contructors
    public HistoricalFigure(String name, List<String> otherName, String category, String birth,
            String death) {
        this.name = name;
        this.otherName = otherName;
        this.category = category;
        this.birth = birth;
        this.death = death;
    }
    
}
