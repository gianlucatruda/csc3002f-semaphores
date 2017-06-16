/**
 * This class is simply to encode branch and duration pairs as a single object.
 * Created by gianlucatruda on 14/06/2017.
 */
class Voyage {
    private final int branch;
    private final int duration;

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
