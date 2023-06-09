package explore.location;
public class TourismLocation {
    
    private String name;
    private String location;
    private String recYear;
    private String category;
    
    // Getters
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public String getRecYear() {
        return recYear;
    }
    public String getCategory() {
        return category;
    }

    // Contructors
    public TourismLocation(String name, String location, String recYear, String category) {
        this.name = name;
        this.location = location;
        this.recYear = recYear;
        this.category = category;
    }
}
