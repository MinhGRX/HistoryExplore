package explore.event;

public class VietNamWar extends Event {
    private String homeForce;
    private String enemyForce;
    private String result;
    public String getHomeForce() {
        return homeForce;
    }
    public String getEnemyForce() {
        return enemyForce;
    }
    public String getResult() {
        return result;
    }
    public VietNamWar(String name, String occurTime, String location, String homeForce, String enemyForce,
            String result) {
        super(name, occurTime, location);
        this.homeForce = homeForce;
        this.enemyForce = enemyForce;
        this.result = result;
    } 
}
// https://vi.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia