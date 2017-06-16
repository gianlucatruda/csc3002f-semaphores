/**
 * Created by gianlucatruda on 14/06/2017.
 */
class Voyage {
    @SuppressWarnings("CanBeFinal")
    private final int branch;
    @SuppressWarnings("CanBeFinal")
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
