public class HistoricalFigure {
    
    private String name;
    private String alternativeName;
    private String dateOfBirth;
    private String dateOfDeath;
    private String hometown;
    private String description;
    
    
    // Getters
    public String getName() {
        return name;
    }
    public String getAlternativeName() {
        return alternativeName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getDateOfDeath() {
        return dateOfDeath;
    }
    public String getHometown() {
        return hometown;
    }
    public String getDescription() {
        return description;
    }

    // Contructors
    public HistoricalFigure(String name, String alternativeName, String dateOfBirth, String dateOfDeath,
            String hometown, String description) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.hometown = hometown;
        this.description = description;
    }
}
