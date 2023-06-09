package explore.dynasty;
public class Dynasty {
    private String name;
    private String founder;
    private String capital;
    private String reignTime;

    // Getters
    public String getName() {
        return name;
    }
    public String getFounder() {
        return founder;
    }
    public String getCapital() {
        return capital;
    }
    public String getReignTime() {
        return reignTime;
    }

    // Contructors
    public Dynasty(String name, String founder, String capital, String reignTime) {
        this.name = name;
        this.founder = founder;
        this.capital = capital;
        this.reignTime = reignTime;
    }    
}
