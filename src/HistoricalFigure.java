public class HistoricalFigure {
    
    private String name;
    private String otherName;
    private String lifeTime;
    private String dynasty;
    private String category;
    private String family;
    
    
    // Getters
    public String getName() {
        return name;
    }
    public String getOtherName() {
        return otherName;
    }
    public String getLifeTime() {
        return lifeTime;
    }
    public String getDynasty() {
        return dynasty;
    }
    public String getCategory() {
        return category;
    }
    public String getFamily() {
        return family;
    }

    // Contructors
    public HistoricalFigure(String name, String otherName, String lifeTime, String family, String dynasty, String category) {
        this.name = name;
        this.otherName = otherName;
        this.lifeTime = lifeTime;
        this.family = family;
        this.dynasty = dynasty;
        this.category = category;
    }
}
