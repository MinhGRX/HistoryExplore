package explore.event;

public class Event {
    private String name;
    private String occurTime;
    private String location;
    
    public String getName() {
        return name;
    }
    public String getOccurTime() {
        return occurTime;
    }
    public String getLocation() {
        return location;
    }

    public Event(String name, String occurTime, String location) {
        this.name = name;
        this.occurTime = occurTime;
        this.location = location;
    }
}
