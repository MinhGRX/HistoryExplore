public class HistoricalFigure {
    
    private String name;
    private String otherName;
    private String dateOfBirth;
    private String dateOfDeath;
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
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getDateOfDeath() {
        return dateOfDeath;
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
    public HistoricalFigure(String name, String otherName, String dateOfBirth, String dateOfDeath,
            String dynasty, String category) {
        this.name = name;
        this.otherName = otherName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.dynasty = dynasty;
        this.category = category;
    }
}
