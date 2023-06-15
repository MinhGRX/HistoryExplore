package explore.event;

public class Festival extends Event{
    private String relativeFigures;
    private String location;
    
    public String getRelativeFigures() {
        return relativeFigures;
    }
    public String getLocation() {
        return location;
    } 

    public Festival(String name, String occurTime, String location, String relativeFigures) {
        super(name, occurTime);
        this.relativeFigures = relativeFigures;
        this.location = location;
    } 
}
// https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam