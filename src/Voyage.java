/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Voyage {
    private int branch;
    private int duration;

    public Voyage(int b, int d) {
        branch = b;
        duration = d;
    }

    public int getBranch() {
        return branch;
    }

    public int getDuration() {
        return duration;
    }
}
