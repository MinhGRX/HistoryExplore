package explore.event;

import java.util.List;

public class Festival extends Event{
    private List<String> relativeFigures;
    
    public List<String> getRelativeFigures() {
        return relativeFigures;
    }

    public Festival(String name, String occurTime, String location, List<String> relativeFigures) {
        super(name, occurTime, location);
        this.relativeFigures = relativeFigures;
    }  
}
// https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam