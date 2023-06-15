package explore.event;

public class Event {
    private String name;
    private String occurTime;
    
    public String getName() {
        return name;
    }
    public String getOccurTime() {
        return occurTime;
    }

    public Event(String name, String occurTime) {
        this.name = name;
        this.occurTime = occurTime;
    }
}
